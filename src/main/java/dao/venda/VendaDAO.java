package dao.venda;

import dao.IDAO;
import dao.cliente.CartaoDeCreditoDAO;
import dao.cliente.EnderecoDAO;
import model.EntidadeDominio;
import model.cliente.CartaoDeCredito;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import model.venda.Venda;
import utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO implements IDAO {

    private final CartaoDeCreditoDAO cartaoDeCreditoDAO = new CartaoDeCreditoDAO();
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {

//        Venda venda = (Venda) entidade;
//        Conexao conexao = new Conexao();
//        Connection connection = null;
//
//        try {
//            connection = conexao.getConexao();
//
//            Cliente cliente = venda.getCliente();
//
//            String sql = "INSERT INTO vendas (vnd_id, vnd_cli_usr_id, vnd_end_entrega_id, vnd_preco_total, vnd_frete, vnd_dt_compra, vnd_dt_envio, vnd_dt_entrega, vnd_pagamento_aprovado, vnd_status)" +
//                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//            PreparedStatement pstm = connection.prepareStatement(sql);
//            pstm.setLong(2, cliente.getId());
//
//            /// adicionar itens produto
//            pstm.execute();
//
//
//            return cliente;
//
//        } catch (SQLException | ClassNotFoundException e) {
//
//            /*
//             * TODO melhorar comportamentos em caso de exceção
//             */
//
//            System.err.println(e.getMessage());
//            e.printStackTrace();
//        } finally {
//            conexao.fecharConexao(connection);
//        }
//
//        return entidade;
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
        Cliente cliente = venda.getCliente();

        Conexao conexao = new Conexao();
        Connection connection = null;

        try {
            connection = conexao.getConexao();

            String sql;
            PreparedStatement pstm = null;

            switch (operacao) {
                case "listar" -> {
                    if (venda.getId() == null) {
                        // venda ainda não foi finalizada/salva, portanto iremos apenas montar uma venda provisória
                        // com base na request e/ou dados default

                        Endereco endereco = venda.getEnderecoEntrega();
                        CartaoDeCredito cartao = venda.getCartao();

                        endereco.setCliente(cliente);
                        cartao.setCliente(cliente);

                        Endereco enderecoEntrega;
                        if (endereco.getId() == null)
                            enderecoEntrega = (Endereco) enderecoDAO.listar(endereco, "listar").get(0);
                        else
                            enderecoEntrega = (Endereco) enderecoDAO.listar(endereco, "listarUnico").get(0);

                        CartaoDeCredito cartaoPreferencial;
                        if (cartao.getId() == null)
                            cartaoPreferencial = (CartaoDeCredito) cartaoDeCreditoDAO.listar(cartao, "findCartaoPreferencial").get(0);
                        else
                            cartaoPreferencial = (CartaoDeCredito) cartaoDeCreditoDAO.listar(cartao, "listarUnico").get(0);

                        venda.setCartao(cartaoPreferencial);
                        venda.setEnderecoEntrega(enderecoEntrega);

                        return List.of(venda);
                    }

                    sql = "SELECT * FROM vendas where vnd_id = ?";

                    pstm = connection.prepareStatement(sql);
                    pstm.setLong(1, venda.getId());
                }

                case "listarTodos" -> {
                    sql = "SELECT * FROM clientes";
                    pstm = connection.prepareStatement(sql);
                }

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
