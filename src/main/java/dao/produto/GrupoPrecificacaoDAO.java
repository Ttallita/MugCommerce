package dao.produto;

import dao.IDAO;
import model.EntidadeDominio;
import model.produto.GrupoPrecificacao;
import utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrupoPrecificacaoDAO implements IDAO {
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
        GrupoPrecificacao grupo = (GrupoPrecificacao) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;
        try {
            connection = conexao.getConexao();

            String sql = null;
            PreparedStatement pstm = null;

            if(operacao.equals("listar")) {
                sql = "SELECT * FROM grupos_precificacao";
                pstm = connection.prepareStatement(sql);
            } else if(operacao.equals("listarUnico")) {
                sql = "SELECT * FROM grupos_precificacao WHERE grp_id = ?";
                pstm = connection.prepareStatement(sql);
                pstm.setLong(1, grupo.getId());
            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> grupos = new ArrayList<>();
            while (rs.next()) {
                GrupoPrecificacao grupoConsulta = new GrupoPrecificacao();
                grupoConsulta.setId(rs.getLong("grp_id"));
                grupoConsulta.setNome(rs.getString("grp_nome"));
                grupoConsulta.setMargemLucro(rs.getDouble("grp_margem_lucro"));
                grupos.add(grupoConsulta);
            }

            return grupos;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return null;
    }
}
