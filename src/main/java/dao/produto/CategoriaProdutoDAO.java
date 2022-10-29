package dao.produto;

import dao.IDAO;
import model.EntidadeDominio;
import model.produto.Produto;
import utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class CategoriaProdutoDAO implements IDAO {

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
        Produto produto = (Produto) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.getConexao();

            String sql = "DELETE from categorias_produtos WHERE ctp_pro_id = ?;";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, produto.getId());

            pstm.execute();

            return produto;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(conn);
        }

        return null;
    }

    @Override
    public List<EntidadeDominio> listar(EntidadeDominio entidade, String operacao) {
        return null;
    }
}
