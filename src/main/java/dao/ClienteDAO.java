package dao;

import model.EntidadeDominio;
import model.Usuario;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import utils.Conexao;

import java.sql.*;
import java.util.List;

public class ClienteDAO implements IDAO {

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        Conexao conexao = new Conexao();
        Connection connection;
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


        } catch (SQLException | ClassNotFoundException e) {

            /**
             * TODO melhorar comportamentos em caso de exceção
             */

            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return entidade;
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
