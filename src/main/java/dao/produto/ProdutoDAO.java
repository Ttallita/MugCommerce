package dao.produto;

import dao.IDAO;
import model.EntidadeDominio;
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

            String sql = "INSERT INTO produto (cli_usr_id, cli_nome, cli_sobrenome, cli_cpf, cli_dt_nasc, cli_genero, cli_telefone_num, cli_telefone_ddd, cli_telefone_tp)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();

            long idCliente = 0L;

            while (rs.next()) {
                idCliente = rs.getLong(1);
            }

            return null;
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
