package dao.solicitacao;

import dao.IDAO;
import dao.venda.VendaDAO;
import model.EntidadeDominio;
import model.solicitacao.Cancelamento;
import model.solicitacao.StatusSolicitacaoType;
import model.venda.Venda;
import utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CancelamentoDAO implements IDAO {

    private VendaDAO vendaDAO = new VendaDAO();

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {

        // TODO mudar status da compra para "Cancelada"

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

        Cancelamento cancelamento = (Cancelamento) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;

        try {
            connection = conexao.getConexao();

            String sql;
            PreparedStatement pstm = null;
            if(operacao.equals("listar")) {
                sql = "SELECT * FROM cancelamentos WHERE ccl_cli_usr_id = ?";

                pstm = connection.prepareStatement(sql);
                pstm.setLong(1, cancelamento.getCliente().getUsuario().getId());
            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> cancelamentos = new ArrayList<>();
            while (rs.next()) {
                Cancelamento cancelamentoConsulta = new Cancelamento(cancelamento.getCliente());
                cancelamentoConsulta.setId(rs.getLong("ccl_id"));

                Venda vendaConsulta = new Venda();
                vendaConsulta.setId(rs.getLong("ccl_vnd_id"));
                cancelamentoConsulta.setVenda((Venda) vendaDAO.listar(vendaConsulta, "listarUnico").get(0));

                cancelamentoConsulta.setCliente(cancelamento.getCliente());
                cancelamentoConsulta.setData(rs.getTimestamp("ccl_data").toLocalDateTime().toLocalDate());
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
