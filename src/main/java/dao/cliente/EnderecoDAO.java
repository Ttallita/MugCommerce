package dao.cliente;

import dao.IDAO;
import model.EntidadeDominio;
import model.cliente.endereco.Endereco;
import model.cliente.endereco.EnderecoType;
import utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO implements IDAO {

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        Endereco endereco = (Endereco) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;
        try {
            conn = conexao.getConexao();

            String sql = "INSERT INTO enderecos (end_cli_usr_id, end_tp, end_apelido, end_tp_logradouro, end_logradouro," +
                    " end_num, end_bairro, end_cep, end_cidade, end_estado, end_observacao, end_tp_residencia)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setLong(1,endereco.getCliente().getUsuario().getId());
            pstm.setString(2, endereco.getTipoEndereco().name());
            pstm.setString(3, endereco.getApelido());
            pstm.setString(4, endereco.getTipoLogradouro());
            pstm.setString(5, endereco.getLogradouro());
            pstm.setInt(6, endereco.getNumero());
            pstm.setString(7, endereco.getBairro());
            pstm.setString(8, endereco.getCep());
            pstm.setString(9, endereco.getCidade());
            pstm.setString(10, endereco.getEstado());
            pstm.setString(11, endereco.getObservacoes());
            pstm.setString(12, endereco.getTipoResidencia());

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
        Endereco endereco = (Endereco) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;
        try {
            conn = conexao.getConexao();

            String sql = "UPDATE enderecos SET end_tp = ?, end_apelido = ?, end_tp_logradouro = ?, end_logradouro = ?," +
                    " end_num = ?, end_bairro = ?, end_cep = ?, end_cidade = ?, end_estado = ?, " +
                    " end_observacao = ?, end_tp_residencia = ? WHERE end_id = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,endereco.getTipoEndereco().name());
            pstm.setString(2, endereco.getApelido());
            pstm.setString(3, endereco.getTipoLogradouro());
            pstm.setString(4, endereco.getLogradouro());
            pstm.setInt(5, endereco.getNumero());
            pstm.setString(6, endereco.getBairro());
            pstm.setString(7, endereco.getCep());
            pstm.setString(8, endereco.getCidade());
            pstm.setString( 9, endereco.getEstado());
            pstm.setString(10, endereco.getObservacoes());
            pstm.setString(11, endereco.getTipoResidencia());
            pstm.setLong(12, endereco.getId());

            pstm.execute();

            return endereco;
        }catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }finally {
            conexao.fecharConexao(conn);
        }
    }

    @Override
    public EntidadeDominio deletar(EntidadeDominio entidade) {
        Endereco endereco = (Endereco) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;
        try {
            conn = conexao.getConexao();

            String sql = "DELETE FROM enderecos where end_id = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, endereco.getId());

            pstm.execute();

            return endereco;
        }catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }finally {
            conexao.fecharConexao(conn);
        }

        return null;
    }

    @Override
    public List<EntidadeDominio> listar(EntidadeDominio entidade, String operacao) {
        Endereco endereco = (Endereco) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;
        try {
            conn = conexao.getConexao();

            String sql;
            PreparedStatement pstm = null;

            if(operacao.equals("listar")) {
                sql = "SELECT * FROM enderecos where end_cli_usr_id = ?";

                pstm = conn.prepareStatement(sql);
                pstm.setLong(1,endereco.getCliente().getUsuario().getId());
            } else if(operacao.equals("listarUnico")) {
                sql = "SELECT * FROM enderecos where end_cli_usr_id = ? AND end_id = ?";

                pstm = conn.prepareStatement(sql);
                pstm.setLong(1, endereco.getCliente().getUsuario().getId());
                pstm.setLong(2, endereco.getId());
            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> enderecos = new ArrayList<>();
            while (rs.next()) {
                Endereco enderecoConsulta = new Endereco();
                enderecoConsulta.setId(rs.getLong("end_id"));
                enderecoConsulta.setCliente(endereco.getCliente());
                enderecoConsulta.setTipoEndereco(EnderecoType.valueOf(rs.getString("end_tp")));
                enderecoConsulta.setTipoResidencia(rs.getString("end_tp_residencia"));
                enderecoConsulta.setApelido(rs.getString("end_apelido"));
                enderecoConsulta.setTipoResidencia(rs.getString("end_tp_residencia"));
                enderecoConsulta.setTipoLogradouro(rs.getString("end_tp_logradouro"));
                enderecoConsulta.setLogradouro(rs.getString("end_logradouro"));
                enderecoConsulta.setNumero(rs.getInt("end_num"));
                enderecoConsulta.setBairro(rs.getString("end_bairro"));
                enderecoConsulta.setCep(rs.getString("end_cep"));
                enderecoConsulta.setCidade(rs.getString("end_cidade"));
                enderecoConsulta.setEstado(rs.getString("end_estado"));
                enderecoConsulta.setObservacoes(rs.getString("end_observacao"));

                enderecos.add(enderecoConsulta);
            }

            return enderecos;
        }catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }finally {
            conexao.fecharConexao(conn);
        }

        return null;
    }
}
