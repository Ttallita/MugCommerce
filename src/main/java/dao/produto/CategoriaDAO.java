package dao.produto;

import dao.IDAO;
import model.EntidadeDominio;
import model.produto.Categoria;
import utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements IDAO {
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

            String sql;
            PreparedStatement pstm = null;

            if("listar".equals(operacao)) {
                sql = "SELECT * FROM categorias";
                pstm = connection.prepareStatement(sql);
            } else if("findByProduto".equals(operacao)) {
                sql = "select * from categorias c " +
                        "inner join categorias_produtos cp " +
                        "on c.ctg_id = cp.ctp_ctg_id " +
                        "where cp.ctp_pro_id = ?;";
                pstm = connection.prepareStatement(sql);
                pstm.setLong(1, entidade.getId());
            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> categorias = new ArrayList<>();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getLong("ctg_id"));
                categoria.setNome(rs.getString("ctg_nome"));
                categorias.add(categoria);
            }

            return categorias;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return null;
    }

    public void salvarAssociacaoCategoriaProduto(long idProduto, long idCategoria) {
        Conexao conexao = new Conexao();
        Connection connection = null;
        try {
            connection = conexao.getConexao();

            String sql = "INSERT INTO categorias_produtos (ctp_pro_id, ctp_ctg_id) values (?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1, idProduto);
            pstm.setLong(2, idCategoria);
            pstm.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

    }
}
