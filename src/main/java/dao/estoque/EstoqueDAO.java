package dao.estoque;

import dao.IDAO;
import dao.produto.ProdutoDAO;
import model.EntidadeDominio;
import model.cliente.Cliente;
import model.cliente.Telefone;
import model.cliente.endereco.TelefoneType;
import model.estoque.Estoque;
import model.produto.Produto;
import utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO implements IDAO {

    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        Estoque estoque = (Estoque) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;
        try {
            connection = conexao.getConexao();

            String sql = "INSERT INTO estoque (est_pro_id, est_quant)" +
                    " VALUES (?, ?)";

            PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setLong(1, estoque.getProduto().getId());
            pstm.setLong(2, estoque.getQuantidade());
            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();

            long idEstoque = 0L;

            while (rs.next()) {
                idEstoque = rs.getLong(1);
            }

            estoque.setId(idEstoque);

            return estoque;
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
        Estoque estoque = (Estoque) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;
        try {
            connection = conexao.getConexao();

            String sql = "UPDATE estoque SET est_quant = ? WHERE est_id = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1, estoque.getQuantidade());
            pstm.setLong(2, estoque.getId());
            pstm.execute();

            return estoque;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return null;
    }

    @Override
    public EntidadeDominio deletar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public List<EntidadeDominio> listar(EntidadeDominio entidade, String operacao) {
        Estoque estoque = (Estoque) entidade;
        Conexao conexao = new Conexao();
        Connection connection = null;

        try {
            connection = conexao.getConexao();

            String sql;
            PreparedStatement pstm = null;
            if(operacao.equals("listar")) {
                sql = "SELECT * FROM estoque";

                pstm = connection.prepareStatement(sql);
            } else if(operacao.equals("findByIdProduto")) {
                sql = "SELECT * FROM estoque WHERE est_pro_id = ?";

                pstm = connection.prepareStatement(sql);
                pstm.setLong(1, estoque.getProduto().getId());
            } else if(operacao.equals("listarUnico")) {
                sql = "SELECT * FROM estoque WHERE est_id = ?";

                pstm = connection.prepareStatement(sql);
                pstm.setLong(1, estoque.getId());
            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> estoques = new ArrayList<>();
            while (rs.next()) {
                Estoque estoqueConsulta = new Estoque();
                estoqueConsulta.setId(rs.getLong("est_id"));
                estoqueConsulta.setQuantidade(rs.getInt("est_quant"));


                Produto produto = new Produto();
                produto.setId(rs.getLong("est_pro_id"));
                Produto produtoConsulta = (Produto) produtoDAO.listar(produto, "listarUnico")
                        .get(0);

                estoqueConsulta.setProduto(produtoConsulta);

                estoques.add(estoqueConsulta);
            }

            System.err.printf("Listando %d estoques(s)/n", estoques.size());

            return estoques;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return null;
    }
}
