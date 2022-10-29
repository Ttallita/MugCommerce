package dao.solicitacao;

import dao.IDAO;
import dao.produto.ProdutoDAO;
import dao.venda.VendaDAO;
import model.EntidadeDominio;
import model.produto.Produto;
import model.solicitacao.StatusSolicitacaoType;
import model.solicitacao.Troca;
import model.venda.Venda;
import utils.Conexao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TrocaDAO implements IDAO {

    private final VendaDAO vendaDAO = new VendaDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();

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
            if(operacao.equals("listar")) {
                sql = "SELECT * FROM trocas WHERE trc_cli_usr_id = ?";

                pstm = connection.prepareStatement(sql);
                pstm.setLong(1, troca.getCliente().getUsuario().getId());
            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> trocas = new ArrayList<>();
            while (rs.next()) {
                Troca trocaConsulta = new Troca(troca.getCliente());
                trocaConsulta.setId(rs.getLong("trc_id"));

                Produto produtoConsulta = new Produto();
                produtoConsulta.setId(rs.getLong("trc_pro_id"));
                trocaConsulta.setProduto((Produto) produtoDAO.listar(produtoConsulta, "listarUnico").get(0));

                Venda vendaConsulta = new Venda();
                vendaConsulta.setId(rs.getLong("trc_vnd_id"));
                trocaConsulta.setVenda((Venda) vendaDAO.listar(vendaConsulta, "listarUnico").get(0));

                trocaConsulta.setCliente(troca.getCliente());
                trocaConsulta.setData(rs.getTimestamp("trc_data").toLocalDateTime().toLocalDate());
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
