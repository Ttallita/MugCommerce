package dao.cliente;

import dao.IDAO;
import dao.UsuarioDAO;
import model.EntidadeDominio;
import model.Usuario;
import model.cliente.Cliente;
import model.cliente.Telefone;
import model.cliente.endereco.Endereco;
import model.cliente.endereco.TelefoneType;
import utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IDAO {

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        Conexao conexao = new Conexao();
        Connection connection = null;
        try {
            connection = conexao.getConexao();

            String sql = "INSERT INTO clientes (cli_usr_id, cli_nome, cli_sobrenome, cli_cpf, cli_dt_nasc, cli_genero, cli_telefone_num, cli_telefone_ddd, cli_telefone_tp)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            Usuario usuario = (Usuario) new UsuarioDAO().salvar(cliente.getUsuario());


            PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setLong(1, usuario.getId());
            pstm.setString(2, cliente.getNome());
            pstm.setString(3, cliente.getSobrenome());
            pstm.setString(4, cliente.getCpf());
            pstm.setObject(5, cliente.getDataNascimento());
            pstm.setString(6, cliente.getGenero());
            pstm.setString(7, cliente.getTelefone().getNumero());
            pstm.setString(8, cliente.getTelefone().getDdd());
            pstm.setString(9, cliente.getTelefone().getTipo().name());

            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();

            long idCliente = 0L;

            while (rs.next()) {
                idCliente = rs.getLong(1);
            }

            cliente.setId(idCliente);

            Endereco endereco = cliente.getEnderecos().get(0);
            endereco.setCliente(cliente);

            new EnderecoDAO().salvar(endereco);

            return cliente;


        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return entidade;
    }

    @Override
    public EntidadeDominio atualizar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        Conexao conexao = new Conexao();
        Connection connection = null;
        try {
            connection = conexao.getConexao();

            String sql = "UPDATE clientes SET cli_nome = ?, cli_sobrenome = ?, cli_cpf = ?, cli_genero = ?, " +
                    "cli_dt_nasc = ?, cli_telefone_num = ?, cli_telefone_ddd = ?, cli_telefone_tp = ? where cli_usr_id = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getSobrenome());
            pstm.setString(3, cliente.getCpf());
            pstm.setString(4, cliente.getGenero());
            pstm.setObject(5, cliente.getDataNascimento());
            pstm.setString(6, cliente.getTelefone().getNumero());
            pstm.setString(7, cliente.getTelefone().getDdd());
            pstm.setString(8, cliente.getTelefone().getTipo().name());
            pstm.setLong(9, cliente.getUsuario().getId());
            pstm.execute();

            return cliente;
        } catch (Exception e) {
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
        Cliente cliente = (Cliente) entidade;
        Conexao conexao = new Conexao();
        Connection connection = null;
        try {
            connection = conexao.getConexao();

            String sql;
            PreparedStatement pstm = null;
            if(operacao.equals("listarUnico")) {
                sql = "SELECT * FROM clientes where cli_usr_id = ?";

                pstm = connection.prepareStatement(sql);
                pstm.setLong(1, cliente.getUsuario().getId());
            } else if (operacao.equals("listarTodos")) {
                sql = "SELECT * FROM clientes";
                pstm = connection.prepareStatement(sql);
            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> clientes = new ArrayList<>();
            while (rs.next()) {
                Cliente clienteConsulta = new Cliente(new Usuario());
                clienteConsulta.setId(rs.getLong("cli_usr_id"));
                clienteConsulta.setNome(rs.getString("cli_nome"));
                clienteConsulta.setSobrenome(rs.getString("cli_sobrenome"));
                clienteConsulta.setCpf(rs.getString("cli_cpf"));
                clienteConsulta.setDataNascimento(rs.getTimestamp("cli_dt_nasc").toLocalDateTime().toLocalDate());
                clienteConsulta.setGenero(rs.getString("cli_genero"));

                Telefone telefone = new Telefone();
                telefone.setNumero(rs.getString("cli_telefone_num"));
                telefone.setDdd(rs.getString("cli_telefone_ddd"));
                telefone.setTipo(TelefoneType.valueOf(rs.getString("cli_telefone_tp")));

                clienteConsulta.setTelefone(telefone);
                clienteConsulta.setRanking(rs.getInt("cli_rank"));
                clientes.add(clienteConsulta);
            }

            return clientes;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return null;
    }
}
