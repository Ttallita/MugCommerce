package dao.solicitacao;

import dao.IDAO;
import dao.produto.ProdutoDAO;
import dao.venda.VendaDAO;
import model.EntidadeDominio;
import model.cliente.Cliente;
import model.produto.Produto;
import model.solicitacao.StatusSolicitacaoType;
import model.solicitacao.Troca;
import model.venda.Venda;
import utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrocaDAO implements IDAO {

    private VendaDAO vendaDAO = new VendaDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
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
                trocaConsulta.setProduto((Produto) produtoDAO.listar(produtoConsulta, "listaUnico").get(0));

                Venda vendaConsulta = new Venda();
                vendaConsulta.setId(rs.getLong("ccl_vnd_id"));
                trocaConsulta.setVenda((Venda) vendaDAO.listar(vendaConsulta, "listaUnico").get(0));

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
