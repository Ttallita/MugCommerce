package dao;

import model.EntidadeDominio;
import model.Usuario;
import model.UsuarioType;
import utils.Conexao;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
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
            pstm.setString(2, Utils.getSha512(usuario.getSenha()));
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
        Usuario usuario = (Usuario) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.getConexao();

            String sql = "UPDATE usuarios SET usr_senha = ? WHERE usr_id = ?;";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, Utils.getSha512(usuario.getSenha()));
            pstm.setLong(2, usuario.getId());

            pstm.execute();

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
    public EntidadeDominio deletar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public List<EntidadeDominio> listar(EntidadeDominio entidade, String operacao) {
        Usuario usuario = (Usuario) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.getConexao();

            PreparedStatement pstm = null;
            if(operacao.equals("login")) {
                String sql = "SELECT * FROM usuarios WHERE usr_email = ? AND usr_senha = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, usuario.getEmail());
                pstm.setString(2, Utils.getSha512(usuario.getSenha()));
            } else if(operacao.equals("findById")) {
                String sql = "SELECT * FROM usuarios WHERE usr_id = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setLong(1, usuario.getId());
            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> entidadesConsulta = new ArrayList<>();
            while (rs.next()) {
                Usuario usuarioConsulta = new Usuario();
                usuarioConsulta.setId(rs.getLong(1));
                usuarioConsulta.setEmail(rs.getString(2));
                usuarioConsulta.setSenha(rs.getString(3));
                usuarioConsulta.setTipoUsuario(UsuarioType.valueOf(rs.getString(4)));
                usuarioConsulta.setAtivo(rs.getBoolean(5));

                entidadesConsulta.add(usuarioConsulta);
            }

            return entidadesConsulta;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(conn);
        }

        return null;
    }

    public Usuario findUsuarioByEmail(String email) {
        // TODO implementar

        return null;
    }

}
