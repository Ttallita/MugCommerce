package dao;

import com.google.gson.Gson;
import model.Auditoria;
import model.EntidadeDominio;
import model.Usuario;
import org.postgresql.util.PGobject;
import utils.Conexao;
import utils.Utils;

import java.sql.*;
import java.util.List;

public class AuditoriaDAO implements IDAO{
    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        Auditoria auditoria = (Auditoria) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.getConexao();
            String sql = "INSERT INTO auditoria (aud_data, aud_usr_id, aud_tipo, aud_dados)" +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setObject(1, auditoria.getData());
            pstm.setLong(2, auditoria.getUsuario().getId());
            pstm.setString(3, auditoria.getTipo().name());

            PGobject jsonObject = new PGobject();
            jsonObject.setType("json");
            jsonObject.setValue(auditoria.getJson());

            pstm.setObject(4, jsonObject);

            pstm.execute();

            return auditoria;
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
