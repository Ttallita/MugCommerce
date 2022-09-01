package dao;

import model.EntidadeDominio;
import model.cliente.endereco.Endereco;
import utils.Conexao;

import java.sql.*;
import java.util.List;

public class EnderecoDAO implements IDAO{

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        Endereco endereco = (Endereco) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;
        try {
            conn = conexao.getConexao();

            String sql = "INSERT INTO enderecos (end_cli_usr_id, end_tp, end_apelido, end_tp_logradouro, end_logradouro, end_num, end_bairro, end_cep, end_cidade, end_estado, end_pais, end_observacao)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setLong(1,endereco.getCliente().getId());
            pstm.setString(2, endereco.getTipoEndereco().name());
            pstm.setString(3, endereco.getApelido());
            pstm.setString(4, endereco.getTipoLogradouro());
            pstm.setString(5, endereco.getLogradouro());
            pstm.setInt(6, endereco.getNumero());
            pstm.setString(7, endereco.getBairro());
            pstm.setString(8, endereco.getCep());
            pstm.setString(9, endereco.getCidade());
            pstm.setString(10, endereco.getEstado());
            pstm.setString(11, endereco.getPais());
            pstm.setString(12, endereco.getObservacoes());

            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();

            long enderecoId = 0L;

            while (rs.next()) {
                enderecoId = rs.getLong(1);
            }

            endereco.setId(enderecoId);

            return endereco;
        }catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }finally {
            conexao.fecharConexao(conn);
        }
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
