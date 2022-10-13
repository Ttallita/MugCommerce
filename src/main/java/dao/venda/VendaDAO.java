package dao.venda;

import dao.IDAO;
import dao.cliente.CartaoDeCreditoDAO;
import dao.cliente.CupomDAO;
import dao.cliente.EnderecoDAO;
import model.EntidadeDominio;
import model.carrinho.ItemCarrinho;
import model.cliente.CartaoDeCredito;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import model.cupom.Cupom;
import model.venda.Venda;
import model.venda.VendaType;
import utils.Conexao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class VendaDAO implements IDAO {

    private final CartaoDeCreditoDAO cartaoDeCreditoDAO = new CartaoDeCreditoDAO();
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();
    private final CupomDAO cupomDAO = new CupomDAO();

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {

        Venda venda = (Venda) entidade;

        Cliente cliente = venda.getCliente();
        Cupom cupom = new Cupom();
        cupom.setCliente(cliente);

        venda.setCupons(getCuponsVenda(cupom, venda)); // necessário definir os cupons antes de calcular o valor da venda

        Conexao conexao = new Conexao();
        Connection connection = null;

        try {
            connection = conexao.getConexao();

            cupomDAO.listar(cupom, "listar");

            String sql = "INSERT INTO vendas (vnd_cli_usr_id, vnd_end_entrega_id, vnd_preco_total, vnd_frete, vnd_dt_compra, vnd_dt_envio, vnd_dt_entrega, vnd_pagamento_aprovado, vnd_status)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setLong(1, cliente.getUsuario().getId());
            pstm.setDouble(2, venda.getEnderecoEntrega().getId());
            pstm.setDouble(3, venda.calculaTotalEntrega()); ///
            pstm.setDouble(4, venda.calculaFrete());
            pstm.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            pstm.setTimestamp(6, null); // sem data envio
            pstm.setTimestamp(7, null); // sem data entrega
            pstm.setBoolean(8, false); // pagamento aprovado
            pstm.setString(9, VendaType.EM_PROCESSAMENTO.name());

            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();
            long idVenda = 0L;
            while (rs.next()) {
                idVenda = rs.getLong(1);
            }

            for (ItemCarrinho item : venda.getCarrinho().getItensCarrinho()) {
                sql = "INSERT INTO produtos_em_venda (prv_pro_id, prv_vnd_id, prv_quant, prv_em_troca) " +
                        "VALUES (?, ?, ?, ?)";

                pstm = connection.prepareStatement(sql);

                pstm.setLong(1, item.getProduto().getId());
                pstm.setLong(2, idVenda);
                pstm.setInt(3, item.getQuant());
                pstm.setBoolean(4, false);

                pstm.execute();
            }

            // TODO Descontar produtos comprados do estoque

            for (CartaoDeCredito cartao : venda.getCartoes()){
                sql = "INSERT INTO cartoes_em_venda (crv_vnd_id, crv_crt_id) " +
                        "VALUES (?, ?)";

                pstm = connection.prepareStatement(sql);

                pstm.setLong(1, idVenda);
                pstm.setLong(2, cartao.getId());

                pstm.execute();
            }

            for (Cupom c : venda.getCupons()){
                cupomDAO.deletar((EntidadeDominio) c);
            }

            return venda;

        } catch (Exception e) {
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
        Venda venda = (Venda) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;

        try {
            connection = conexao.getConexao();

            String sql;
            PreparedStatement pstm = null;

            switch (operacao) {
                case "listar" -> {
                    if (venda.getId() == null) {
                        return criaVendaProvisoria(venda);
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

            return vendas;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return null;
    }

    /**
     * venda ainda não foi finalizada/salva, portanto iremos apenas montar uma venda provisória
     * com base na request e/ou dados default
     * @param venda
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<EntidadeDominio> criaVendaProvisoria(Venda venda) {
        Cliente cliente = venda.getCliente();
        Endereco endereco = venda.getEnderecoEntrega();
        CartaoDeCredito cartao = new CartaoDeCredito();
        Cupom cupom = new Cupom();

        endereco.setCliente(cliente);
        cartao.setCliente(cliente);
        cupom.setCliente(cliente);

        Endereco enderecoEntrega;
        if (endereco.getId() == null)
            enderecoEntrega = (Endereco) enderecoDAO.listar(endereco, "listar").get(0);
        else
            enderecoEntrega = (Endereco) enderecoDAO.listar(endereco, "listarUnico").get(0);

        List<? extends EntidadeDominio> cartoes = cartaoDeCreditoDAO.listar(cartao, "listar");
        List<? extends EntidadeDominio> cupons = cupomDAO.listar(cupom, "listar");

        venda.setEnderecoEntrega(enderecoEntrega);
        venda.setCartoes((List<CartaoDeCredito>) cartoes);
        venda.setCupons((List<Cupom>) cupons);

        return List.of(venda);
    }

    @SuppressWarnings("unchecked")
    private List<Cupom> getCuponsVenda(Cupom cupom, Venda venda) {
        List<Long> ids = venda.getCupons().stream().map(Cupom::getId).toList();
        List<? extends EntidadeDominio> cupons = cupomDAO.listar(cupom, "listar");

        return (List<Cupom>) cupons.stream().filter(c -> ids.contains(c.getId())).toList();
    }

}
