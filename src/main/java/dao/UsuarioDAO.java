package dao;

import model.EntidadeDominio;
import model.Usuario;
import utils.Conexao;

import java.sql.*;
import java.util.List;

public class UsuarioDAO implements IDAO {
    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        Usuario usuario = (Usuario) entidade;
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.getConexao();
            String sql = "INSERT INTO usuarios (usr_email, usr_senha, usr_tipo, usr_ativo)" +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, usuario.getEmail());
            pstm.setString(2, usuario.getSenha());
            pstm.setString(3, usuario.getTipoUsuario().toString());
            pstm.setBoolean(4, usuario.isAtivo());

            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();

            long idUser = 0L;

            while (rs.next()) {
                idUser = rs.getLong(1);
            }

            usuario.setId(idUser);

            return usuario;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(conn);
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
        return null;
    }
}
