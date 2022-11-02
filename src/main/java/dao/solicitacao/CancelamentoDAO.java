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
import java.util.List;

public class CancelamentoDAO implements IDAO {

    private final VendaDAO vendaDAO = new VendaDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final EstoqueDAO estoqueDAO = new EstoqueDAO();
    private final CupomDAO cupomDAO = new CupomDAO();

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

                double totalVenda = cancelamentoConsulta.getVenda().getCalculaTotalVendaSemDesconto();

                Cupom cupomTroca = new Cupom();
                cupomTroca.setValor(totalVenda);
                cupomTroca.setNome("Troca do dia " + Utils.formataLocalDateBR(LocalDate.now()));
                cupomTroca.setDescricao("Cupom gerado por cancelamento de venda");
                cupomTroca.setTipo(CupomType.TROCA);
                cupomTroca.setCliente(cancelamentoConsulta.getCliente());

                cupomDAO.salvar(cupomTroca);

                // Nesse momento estaremos retornando todos os itens da compra para o estoque
                if(cancelamento.isReentradaEstoque()) {
                    List<ItemCarrinho> itensCarrinho = cancelamentoConsulta.getVenda()
                            .getCarrinho()
                            .getItensCarrinho();

                    itensCarrinho.forEach(item -> {
                        Estoque estoque = new Estoque();
                        estoque.setProduto(item.getProduto());

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
