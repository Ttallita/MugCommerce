package dao.venda;

import dao.IDAO;
import dao.cliente.CartaoDeCreditoDAO;
import model.EntidadeDominio;
import model.cliente.CartaoDeCredito;
import model.cliente.Cliente;
import model.cliente.Telefone;
import model.venda.Venda;
import utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendaDAO implements IDAO {

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
        return null;
    }

    @Override
    public List<EntidadeDominio> listar(EntidadeDominio entidade, String operacao) {
        Venda venda = (Venda) entidade;
        Conexao conexao = new Conexao();
        Connection connection = null;

        try {
            connection = conexao.getConexao();

            String sql;
            PreparedStatement pstm = null;
            if(operacao.equals("listar")) {

                if (venda.getId() == null){
                    CartaoDeCredito cartaoPreferencial =
                            (CartaoDeCredito) new CartaoDeCreditoDAO().listar(new CartaoDeCredito(), "findCartaoPreferencial").get(0);

                    venda.setCartoesdeCreditos(List.of(cartaoPreferencial));

                    return List.of(venda);
                }

                sql = "SELECT * FROM vendas where vnd_id = ?";

                pstm = connection.prepareStatement(sql);
                pstm.setLong(1, venda.getId());
            } else if (operacao.equals("listarTodos")) {
                sql = "SELECT * FROM clientes";
                pstm = connection.prepareStatement(sql);
            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> vendas = new ArrayList<>();
            while (rs.next()) {
                Venda vendaConsulta = new Venda();
                vendaConsulta.setId(rs.getLong("vnd_id"));

                vendas.add(vendaConsulta);
            }

            System.err.printf("Listando %d venda(s)/n", vendas.size());

            return vendas;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return null;
    }

}
