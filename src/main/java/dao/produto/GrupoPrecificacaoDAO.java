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
        Conexao conexao = new Conexao();
        Connection connection = null;
        try {
            connection = conexao.getConexao();

            String sql = "SELECT * FROM grupos_precificacao";
            PreparedStatement pstm = connection.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> grupos = new ArrayList<>();
            while (rs.next()) {
                GrupoPrecificacao grupo = new GrupoPrecificacao();
                grupo.setNome(rs.getString("grp_nome"));
                grupo.setMargemLucro(rs.getDouble("grp_margem_lucro"));
                grupos.add(grupo);
            }

            return grupos;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return null;
    }
}
