package dao.solicitacao;

import dao.IDAO;
import dao.cliente.ClienteDAO;
import dao.cliente.CupomDAO;
import dao.estoque.EstoqueDAO;
import dao.produto.ProdutoDAO;
import dao.venda.VendaDAO;
import model.EntidadeDominio;
import model.Usuario;
import model.carrinho.ItemCarrinho;
import model.cliente.Cliente;
import model.cupom.Cupom;
import model.cupom.CupomType;
import model.estoque.Estoque;
import model.produto.Produto;
import model.solicitacao.StatusSolicitacaoType;
import model.solicitacao.Troca;
import model.venda.Venda;
import utils.Conexao;
import utils.Utils;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TrocaDAO implements IDAO {

    private final VendaDAO vendaDAO = new VendaDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final EstoqueDAO estoqueDAO = new EstoqueDAO();
    private final CupomDAO cupomDAO = new CupomDAO();

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        Troca troca = (Troca) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;

        try {
            connection = conexao.getConexao();

            String sql = "INSERT INTO trocas (trc_pro_id, trc_vnd_id, trc_cli_usr_id, trc_data, trc_status)" +
                    " VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setLong(1, troca.getProduto().getId());
            pstm.setLong(2, troca.getVenda().getId());
            pstm.setLong(3, troca.getCliente().getUsuario().getId());
            pstm.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            pstm.setString(5, StatusSolicitacaoType.SOLICITADA.name());

            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();
            while (rs.next()) {
                troca.setId(rs.getLong(1));
            }

            produtoDAO.marcarEmTrocaProdutoVenda(troca.getProduto(), troca.getVenda());

            return troca;

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
        Troca troca = (Troca) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;

        try {
            connection = conexao.getConexao();

            String sql = "UPDATE trocas SET trc_status = ? WHERE trc_id = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, troca.getStatus().name());
            pstm.setLong(2, troca.getId());

            pstm.execute();

            // Aqui estamos gerando o cupom de troca para o cliente.
            if(troca.getStatus().equals(StatusSolicitacaoType.REALIZADA)) {
                Troca trocaConsulta = (Troca) listar(troca, "listarUnico")
                        .get(0);

                ItemCarrinho itemCarrinho = trocaConsulta.getVenda()
                        .getCarrinho()
                        .getItensCarrinho()
                        .stream()
                        .filter(item -> item.getProduto().getId().equals(trocaConsulta.getProduto().getId()))
                        .findFirst()
                        .orElse(null);

                if(itemCarrinho == null)
                    throw new RuntimeException("Erro ao tentar encontrar item da troca na venda");

                Cupom cupomTroca = new Cupom();
                cupomTroca.setValor(itemCarrinho.getProduto().getValorVenda() * itemCarrinho.getQuant());
                cupomTroca.setNome("Troca do dia " + Utils.formataLocalDateBR(LocalDate.now()));
                cupomTroca.setDescricao("Cupom gerado por troca de produto");
                cupomTroca.setTipo(CupomType.TROCA);
                cupomTroca.setCliente(troca.getCliente());

                cupomDAO.salvar(cupomTroca);

                // Nesse momento estaremos retornando os itens da troca para o estoque
                if(troca.isReentradaEstoque()) {
                    Estoque estoque = new Estoque();
                    estoque.setProduto(trocaConsulta.getProduto());

                    Estoque estoqueConsulta = (Estoque) estoqueDAO.listar(estoque, "findByIdProduto")
                            .get(0);
                    estoqueConsulta.setQuantidade(estoqueConsulta.getQuantidade() + itemCarrinho.getQuant());

                    estoqueDAO.atualizar(estoqueConsulta);
                }
            }

            return troca;
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

        Troca troca = (Troca) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;

        try {
            connection = conexao.getConexao();

            String sql;
            PreparedStatement pstm = null;

            switch (operacao) {

                case "listar" -> {
                    sql = "SELECT * FROM trocas WHERE trc_cli_usr_id = ?";

                    pstm = connection.prepareStatement(sql);
                    pstm.setLong(1, troca.getCliente().getUsuario().getId());
                }

                case "listarUnico", "listarJson" -> {
                    sql = "SELECT * FROM trocas WHERE trc_id = ?";

                    pstm = connection.prepareStatement(sql);
                    pstm.setLong(1, troca.getId());
                }

                case "listarTodos" -> {
                    sql = "SELECT * FROM trocas";

                    pstm = connection.prepareStatement(sql);
                }

            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> trocas = new ArrayList<>();
            while (rs.next()) {

                Cliente clienteConsulta = new Cliente(new Usuario());
                clienteConsulta.getUsuario().setId(rs.getLong("trc_cli_usr_id"));
                Cliente cliente = (Cliente) clienteDAO.listar(clienteConsulta, "listarUnico").get(0);

                Troca trocaConsulta = new Troca(cliente);
                trocaConsulta.setId(rs.getLong("trc_id"));

                Produto produtoConsulta = new Produto();
                produtoConsulta.setId(rs.getLong("trc_pro_id"));
                trocaConsulta.setProduto((Produto) produtoDAO.listar(produtoConsulta, "listarUnico").get(0));

                Venda vendaConsulta = new Venda();
                vendaConsulta.setId(rs.getLong("trc_vnd_id"));
                trocaConsulta.setVenda((Venda) vendaDAO.listar(vendaConsulta, "listarUnico").get(0));

                trocaConsulta.setCliente(cliente);
                trocaConsulta.setData(rs.getTimestamp("trc_data").toLocalDateTime());
                trocaConsulta.setStatus(StatusSolicitacaoType.valueOf(rs.getString("trc_status")));

                trocas.add(trocaConsulta);
            }

            System.err.printf("Listando %d troca(s)\n", trocas.size());

            return trocas;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return null;
    }

}
