package dao.solicitacao;

import dao.IDAO;
import dao.cliente.ClienteDAO;
import dao.cliente.CupomDAO;
import dao.estoque.EstoqueDAO;
import dao.venda.VendaDAO;
import model.EntidadeDominio;
import model.Usuario;
import model.carrinho.ItemCarrinho;
import model.cliente.Cliente;
import model.cupom.Cupom;
import model.cupom.CupomType;
import model.estoque.Estoque;
import model.solicitacao.Cancelamento;
import model.solicitacao.StatusSolicitacaoType;
import model.solicitacao.Troca;
import model.venda.StatusVendaType;
import model.venda.Venda;
import utils.Conexao;
import utils.Utils;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CancelamentoDAO implements IDAO {

    private final VendaDAO vendaDAO = new VendaDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final EstoqueDAO estoqueDAO = new EstoqueDAO();
    private final CupomDAO cupomDAO = new CupomDAO();

    private final TrocaDAO trocaDAO = new TrocaDAO();

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        Cancelamento cancelamento = (Cancelamento) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;

        try {
            connection = conexao.getConexao();

            String sql = "INSERT INTO cancelamentos (ccl_vnd_id, ccl_cli_usr_id, ccl_data, ccl_status)" +
                    " VALUES (?, ?, ?, ?)";

            PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setLong(1, cancelamento.getVenda().getId());
            pstm.setLong(2, cancelamento.getCliente().getUsuario().getId());
            pstm.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pstm.setString(4, StatusSolicitacaoType.SOLICITADA.name());

            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();
            while (rs.next()) {
                cancelamento.setId(rs.getLong(1));
            }

            Venda venda = cancelamento.getVenda();
            venda.setVendaStatus(StatusVendaType.CANCELADA);

            vendaDAO.atualizar(venda);

            return cancelamento;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return null;
    }

    @Override
    public EntidadeDominio atualizar(EntidadeDominio entidade) {
        Cancelamento cancelamento = (Cancelamento) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;

        try {
            connection = conexao.getConexao();

            String sql = "UPDATE cancelamentos SET ccl_status = ? WHERE ccl_id = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, cancelamento.getStatus().name());
            pstm.setLong(2, cancelamento.getId());

            pstm.execute();

            // Aqui estamos gerando o cupom de troca para o cliente.
            if(cancelamento.getStatus().equals(StatusSolicitacaoType.REALIZADA)) {
                Cancelamento cancelamentoConsulta = (Cancelamento) listar(cancelamento, "listarUnico")
                        .get(0);

                List<ItemCarrinho> itensCarrinho = cancelamentoConsulta.getVenda()
                        .getCarrinho()
                        .getItensCarrinho();

                Cupom cupomTroca = new Cupom();

                Troca trocaConsulta = new Troca();
                trocaConsulta.setVenda(cancelamentoConsulta.getVenda());
                trocaConsulta.setCliente(cancelamentoConsulta.getCliente());

                List<Troca> trocas = trocaDAO.listar(trocaConsulta, "listarTrocasPorVenda")
                        .stream()
                        .map(entidadeDominio -> (Troca) entidadeDominio)
                        .toList();

                double valorRetornado = itensCarrinho.stream()
                        .filter(item -> {
                            if (item.isEmTroca()) {
                                Troca trocaProdutoCarrinho = trocas.stream()
                                        .filter(troca -> troca.getProduto().getId().equals(item.getProduto().getId()))
                                        .findFirst()
                                        .orElse(null);

                                if (trocaProdutoCarrinho == null)
                                    throw new RuntimeException("Não foi encontrado o produto da troca");

                                // Se a troca não foi realizada só atualizaremos seu status e somaremos o valor do produto
                                // para o cupom
                                if (!trocaProdutoCarrinho.getStatus().equals(StatusSolicitacaoType.REALIZADA)) {
                                    trocaProdutoCarrinho.setStatus(StatusSolicitacaoType.RECUSADA);

                                    trocaDAO.atualizar(trocaProdutoCarrinho);

                                    return true;

                                // Caso contrario verificaremos a quantidade de trocada e se tiver sobra, usaremos essa quantidade
                                // para somar no valor do cupom
                                } else {
                                    int quantidade = item.getQuant() - trocaProdutoCarrinho.getQuantidade();

                                    if(quantidade != 0) {
                                        item.setQuant(quantidade);
                                        return true;
                                    } else
                                        return false;
                                }

                            }

                            return true;
                        })
                        .mapToDouble(item -> item.getProduto().getValorVenda() * item.getQuant())
                        .sum();

                cupomTroca.setValor(valorRetornado);
                cupomTroca.setNome("Troca do dia " + Utils.formataLocalDateBR(LocalDate.now()));
                cupomTroca.setDescricao("Cupom gerado por cancelamento de venda");
                cupomTroca.setTipo(CupomType.TROCA);
                cupomTroca.setCliente(cancelamentoConsulta.getCliente());

                cupomDAO.salvar(cupomTroca);

                // Nesse momento estaremos retornando todos os itens da compra para o estoque
                if(cancelamento.isReentradaEstoque()) {
                    itensCarrinho.forEach(item -> {
                        Estoque estoque = new Estoque();
                        estoque.setProduto(item.getProduto());


                        if(item.isEmTroca()) {
                            Troca trocaProdutoAtual = trocas.stream()
                                    .filter(troca -> troca.getProduto().getId().equals(item.getProduto().getId()))
                                    .findFirst()
                                    .orElse(null);

                            if (trocaProdutoAtual == null)
                                throw new RuntimeException("Não foi encontrado o produto da troca");

                            if (trocaProdutoAtual.getStatus().equals(StatusSolicitacaoType.REALIZADA)) {
                                int quantidade = item.getQuant() - trocaProdutoAtual.getQuantidade();

                                if (quantidade != 0)
                                    item.setQuant(quantidade);
                            }
                        }

                        Estoque estoqueConsulta = (Estoque) estoqueDAO.listar(estoque, "findByIdProduto")
                                .get(0);
                        estoqueConsulta.setQuantidade(estoqueConsulta.getQuantidade() + item.getQuant());

                        estoqueDAO.atualizar(estoqueConsulta);
                    });
                }
            }

            return cancelamento;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return null;
    }

    @Override
    public EntidadeDominio deletar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public List<EntidadeDominio> listar(EntidadeDominio entidade, String operacao) {

        Cancelamento cancelamento = (Cancelamento) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;

        try {
            connection = conexao.getConexao();

            String sql;
            PreparedStatement pstm = null;

            switch (operacao) {

                case "listar" -> {
                    sql = "SELECT * FROM cancelamentos WHERE ccl_cli_usr_id = ?";

                    pstm = connection.prepareStatement(sql);
                    pstm.setLong(1, cancelamento.getCliente().getUsuario().getId());
                }

                case "listarUnico", "listarJson" -> {
                    sql = "SELECT * FROM cancelamentos WHERE ccl_id = ?";

                    pstm = connection.prepareStatement(sql);
                    pstm.setLong(1, cancelamento.getId());
                }

                case "listarTodos" -> {
                    sql = "SELECT * FROM cancelamentos";

                    pstm = connection.prepareStatement(sql);
                }

            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> cancelamentos = new ArrayList<>();
            while (rs.next()) {

                Cliente clienteConsulta = new Cliente(new Usuario());
                clienteConsulta.getUsuario().setId(rs.getLong("ccl_cli_usr_id"));
                Cliente cliente = (Cliente) clienteDAO.listar(clienteConsulta, "listarUnico").get(0);

                Cancelamento cancelamentoConsulta = new Cancelamento(cliente);
                cancelamentoConsulta.setId(rs.getLong("ccl_id"));

                Venda vendaConsulta = new Venda();
                vendaConsulta.setId(rs.getLong("ccl_vnd_id"));
                cancelamentoConsulta.setVenda((Venda) vendaDAO.listar(vendaConsulta, "listarUnico").get(0));

                cancelamentoConsulta.setCliente(cliente);
                cancelamentoConsulta.setData(rs.getTimestamp("ccl_data").toLocalDateTime());
                cancelamentoConsulta.setStatus(StatusSolicitacaoType.valueOf(rs.getString("ccl_status")));

                cancelamentos.add(cancelamentoConsulta);
            }

            System.err.printf("Listando %d cancelamento(s)\n", cancelamentos.size());

            return cancelamentos;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return null;
    }

}
