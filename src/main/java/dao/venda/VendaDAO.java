package dao.venda;

import dao.IDAO;
import dao.cliente.CartaoDeCreditoDAO;
import dao.cliente.ClienteDAO;
import dao.cliente.CupomDAO;
import dao.cliente.EnderecoDAO;
import model.EntidadeDominio;
import model.Usuario;
import model.carrinho.Carrinho;
import model.carrinho.ItemCarrinho;
import model.cliente.CartaoDeCredito;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import model.cupom.Cupom;
import model.produto.Produto;
import model.venda.StatusVendaType;
import model.venda.Venda;
import utils.Conexao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO implements IDAO {

    private final CartaoDeCreditoDAO cartaoDeCreditoDAO = new CartaoDeCreditoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
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

            String sql = "INSERT INTO vendas (vnd_cli_usr_id, vnd_end_entrega_id, vnd_end_cobranca_id, vnd_preco_total, vnd_frete, vnd_dt_compra, vnd_dt_envio, vnd_dt_entrega, vnd_pagamento_aprovado, vnd_status)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setLong(1, cliente.getUsuario().getId());
            pstm.setLong(2, venda.getEnderecoEntrega().getId());
            pstm.setLong(3, venda.getEnderecoEntrega().getId()); // cobrança
            pstm.setDouble(4, venda.calculaTotalEntrega()); ///
            pstm.setDouble(5, venda.calculaFrete());
            pstm.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            pstm.setTimestamp(7, null); // sem data envio
            pstm.setTimestamp(8, null); // sem data entrega
            pstm.setBoolean(9, false); // pagamento aprovado
            pstm.setString(10, StatusVendaType.EM_ANALISE.name());

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
        Venda venda = (Venda) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;

        try {
            connection = conexao.getConexao();

            String sql = "UPDATE vendas SET vnd_status = ? WHERE vnd_id = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, venda.getVendaStatus().name());
            pstm.setLong(2, venda.getId());

            pstm.execute();

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

            Cliente cliente = venda.getCliente();
            switch (operacao) {
                case "listarUnico", "listarJson" -> {
                    if (venda.getId() == null) {
                        return criaVendaProvisoria(venda);
                    }

                    sql = "SELECT * FROM vendas where vnd_id = ?";

                    pstm = connection.prepareStatement(sql);
                    pstm.setLong(1, venda.getId());
                }

                case "listar" -> {
                    sql = "SELECT * FROM vendas v WHERE v.vnd_cli_usr_id = ?";
                    pstm = connection.prepareStatement(sql);
                    pstm.setLong(1, cliente.getUsuario().getId());
                }

                case "listarTodos" -> {
                    sql = "SELECT * FROM vendas";
                    pstm = connection.prepareStatement(sql);
                }

            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> vendas = new ArrayList<>();
            while (rs.next()) {
                Venda vendaConsulta = new Venda();

                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("vnd_cli_usr_id"));

                Cliente clienteVenda = new Cliente();
                clienteVenda.setUsuario(usuario);

                clienteVenda = (Cliente) clienteDAO.listar(clienteVenda, "listarUnico").get(0);

                Endereco endereco = new Endereco();
                endereco.setId(rs.getLong("vnd_end_entrega_id"));
                endereco.setCliente(clienteVenda);

                long idVenda = rs.getLong("vnd_id");

                vendaConsulta.setId(idVenda);
                vendaConsulta.setCliente(clienteVenda);
                vendaConsulta.setPrecoTotal(rs.getDouble("vnd_preco_total"));
                vendaConsulta.setFrete(rs.getDouble("vnd_frete"));
                vendaConsulta.setPagamentoAprovado(rs.getBoolean("vnd_pagamento_aprovado"));
                vendaConsulta.setVendaStatus(StatusVendaType.valueOf(rs.getString("vnd_status")));
                vendaConsulta.setDataCompra(rs.getTimestamp("vnd_dt_compra").toLocalDateTime().toLocalDate());
                vendaConsulta.setEnderecoEntrega((Endereco) enderecoDAO.listar(endereco, "listarUnico").get(0));

                Timestamp dtEnvio = rs.getTimestamp("vnd_dt_envio");
                Timestamp dtEntrega = rs.getTimestamp("vnd_dt_entrega");
                vendaConsulta.setDataEnvio(dtEnvio != null ? rs.getTimestamp("vnd_dt_envio").toLocalDateTime().toLocalDate() : null);
                vendaConsulta.setDataEntrega(dtEntrega  != null ? rs.getTimestamp("vnd_dt_entrega").toLocalDateTime().toLocalDate() : null);

                sql = "SELECT * FROM produtos p " +
                        "RIGHT JOIN (SELECT * FROM produtos_em_venda WHERE prv_vnd_id = ?) pev " +
                        "ON p.pro_id = pev.prv_pro_id;";

                pstm = connection.prepareStatement(sql);
                pstm.setLong(1, idVenda);

                Carrinho carrinho = new Carrinho();

                ResultSet rsProdutosVenda = pstm.executeQuery();
                while (rsProdutosVenda.next()) {
                    ItemCarrinho item = new ItemCarrinho();
                    Produto produto = new Produto();

                    produto.setId(rsProdutosVenda.getLong("pro_id"));
                    produto.setNome(rsProdutosVenda.getString("pro_nome"));
                    produto.setValorCompra(rsProdutosVenda.getDouble("pro_valor_compra"));
                    produto.setValorVenda(rsProdutosVenda.getDouble("pro_valor_venda"));
                    produto.setDescricao(rsProdutosVenda.getString("pro_descricao"));
                    produto.setMaterial(rsProdutosVenda.getString("pro_material"));
                    produto.setCodBarras(rsProdutosVenda.getString("pro_cod_barras"));
                    produto.setImagem(rsProdutosVenda.getString("pro_imagem"));

                    item.setProduto(produto);
                    item.setQuant(rsProdutosVenda.getInt("prv_quant"));
                    item.setEmTroca(rsProdutosVenda.getBoolean("prv_em_troca"));

                    carrinho.addItem(item);
                }

                vendaConsulta.setCarrinho(carrinho);
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
        List<? extends EntidadeDominio> cupons = cupomDAO.listar(cupom, "listarTodos");

        venda.setEnderecoEntrega(enderecoEntrega);
        venda.setCartoes((List<CartaoDeCredito>) cartoes);
        venda.setCupons((List<Cupom>) cupons);

        return List.of(venda);
    }

    @SuppressWarnings("unchecked")
    private List<Cupom> getCuponsVenda(Cupom cupom, Venda venda) {
        List<Long> ids = venda.getCupons().stream().map(Cupom::getId).toList();
        List<? extends EntidadeDominio> cupons = cupomDAO.listar(cupom, "listarTodos");

        return (List<Cupom>) cupons.stream().filter(c -> ids.contains(c.getId())).toList();
    }

}
