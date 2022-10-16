package dao.estoque;

import dao.IDAO;
import model.EntidadeDominio;
import model.estoque.Estoque;
import model.estoque.EstoqueHistorico;
import model.estoque.Fornecedor;
import utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstoqueHistoricoDAO implements IDAO {

    private final EstoqueDAO estoqueDAO = new EstoqueDAO();

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        EstoqueHistorico estoqueHistorico = (EstoqueHistorico) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;
        try {
            connection = conexao.getConexao();

            String sql = "INSERT INTO estoque_historico (est_hist_est_id, est_hist_quant, est_hist_data, est_hist_valor_custo, est_hist_fornecedor)" +
                    " VALUES (?, ?, ?, ?, ?)";

            Estoque estoque = (Estoque) estoqueDAO.listar(estoqueHistorico.getEstoque(), "findByIdProduto")
                    .get(0);

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1, estoque.getId());
            pstm.setInt(2, estoqueHistorico.getQuantidade());
            pstm.setObject(3, estoqueHistorico.getDataEntrada());
            pstm.setDouble(4, estoqueHistorico.getValorCusto());
            pstm.setString(5, estoqueHistorico.getFornecedor().getNome());
            pstm.execute();

            estoque.setQuantidade(estoque.getQuantidade() + estoqueHistorico.getQuantidade());

            estoqueDAO.atualizar(estoque);

            return estoqueHistorico;
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
        Conexao conexao = new Conexao();
        Connection connection = null;

        try {
            connection = conexao.getConexao();

            String sql = "SELECT * from estoque_historico";
            PreparedStatement pstm = connection.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> historico = new ArrayList<>();
            while (rs.next()) {
                EstoqueHistorico historicoConsulta = new EstoqueHistorico();
                historicoConsulta.setId(rs.getLong("est_hist_id"));

                Estoque estoque = new Estoque();
                estoque.setId(rs.getLong("est_hist_est_id"));

                Estoque estoqueConsulta = (Estoque) estoqueDAO.listar(estoque, "listarUnico")
                        .get(0);

                historicoConsulta.setEstoque(estoqueConsulta);
                historicoConsulta.setQuantidade(rs.getInt("est_hist_quant"));
                historicoConsulta.setDataEntrada(rs.getTimestamp("est_hist_data").toLocalDateTime());
                historicoConsulta.setValorCusto(rs.getDouble("est_hist_valor_custo"));
                historicoConsulta.setFornecedor(new Fornecedor(rs.getString("est_hist_fornecedor")));

                historico.add(historicoConsulta);
            }

            System.err.println("Listando %d historico do estioque(s) " + historico.size());

            return historico;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return null;
    }
}
