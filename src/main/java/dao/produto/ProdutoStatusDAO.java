package dao.produto;

import dao.IDAO;
import model.EntidadeDominio;
import model.produto.Produto;
import model.produto.ProdutoStatus;
import utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProdutoStatusDAO implements IDAO {

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
        ProdutoStatus status = (ProdutoStatus) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.getConexao();

            deletaStatusAntigo(status.getProduto());

            String sql = "INSERT INTO produtos_status (pst_categoria, pst_prod_id, pst_justificativa)" +
                    " VALUES (?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, status.getCategoriaStatus().name());
            pstm.setLong(2, status.getProduto().getId());
            pstm.setString(3, status.getJustificativa());

            pstm.execute();

            new ProdutoDAO().deletar(status.getProduto());

            return status;
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

    private void deletaStatusAntigo(Produto produto) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.getConexao();

            String sql = "DELETE FROM produtos_status WHERE pst_prod_id = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, produto.getId());

            pstm.execute();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(conn);
        }
    }
}
