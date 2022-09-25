package dao.produto;

import dao.IDAO;
import model.EntidadeDominio;
import model.produto.GrupoPrecificacao;
import model.produto.Produto;
import utils.Conexao;

import java.sql.*;
import java.util.List;

public class ProdutoDAO implements IDAO {

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        Produto produto = (Produto) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;
        try {
            connection = conexao.getConexao();

            String sql = "INSERT INTO produtos (pro_fab_id, pro_grp_id, pro_nome, pro_valor_compra, pro_valor_venda, pro_descricao, pro_material, pro_cod_barras, pro_imagem)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            GrupoPrecificacao grupo  = (GrupoPrecificacao) new GrupoPrecificacaoDAO().listar(produto.getGrupoPrecificacao(), "listarUnico")
                    .get(0);

            double valorVenda = produto.getValorCompra() + (produto.getValorCompra() + (grupo.getMargemLucro() / 100));

            PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setLong(1, produto.getFabricante().getId());
            pstm.setLong(2, grupo.getId());
            pstm.setString(3, produto.getNome());
            pstm.setDouble(4, produto.getValorCompra());
            pstm.setDouble(5, valorVenda);
            pstm.setString(6, produto.getDescricao());
            pstm.setString(7, produto.getMaterial());
            pstm.setString(8, produto.getCodBarras());
            pstm.setString(9, produto.getImagem());

            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();

            long idProduto = 0L;

            while (rs.next()) {
                idProduto = rs.getLong(1);
            }



            produto.setId(idProduto);

            return produto;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
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
