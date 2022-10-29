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
import model.cliente.endereco.EnderecoType;
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

            cupomDAO.listar(cupom, "listar");

            String sql = "INSERT INTO vendas (vnd_cli_usr_id, vnd_end_entrega_id, vnd_preco_total, vnd_frete, vnd_dt_compra, vnd_dt_envio, vnd_dt_entrega, vnd_pagamento_aprovado, vnd_status, vnd_end_cobranca_id)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setLong(1, cliente.getUsuario().getId());
            pstm.setDouble(2, venda.getEnderecoEntrega().getId());

            // TODO precisa gerar um cupom de troca caso o valor da compra esteja negativo
            pstm.setDouble(3, venda.getCalculaTotalEntrega()); ///
            pstm.setDouble(4, venda.calculaFrete());
            pstm.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            pstm.setTimestamp(6, null); // sem data envio
            pstm.setTimestamp(7, null); // sem data entrega
            pstm.setBoolean(8, false); // pagamento aprovado
            pstm.setString(9, StatusVendaType.EM_ANALISE.name());
            pstm.setLong(10, venda.getEnderecoCobranca().getId());

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

            if(venda.getCupons() != null) {
                for (Cupom c : venda.getCupons()){
                    cupomDAO.deletar(c);
                }
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

                Endereco enderecoEntrega = new Endereco();
                enderecoEntrega.setId(rs.getLong("vnd_end_entrega_id"));
                enderecoEntrega.setCliente(clienteVenda);

                Endereco enderecoCobranca = new Endereco();
                enderecoCobranca.setId(rs.getLong("vnd_end_cobranca_id"));
                enderecoCobranca.setCliente(clienteVenda);

                long idVenda = rs.getLong("vnd_id");

                vendaConsulta.setId(idVenda);
                vendaConsulta.setCliente(clienteVenda);
                vendaConsulta.setPrecoTotal(rs.getDouble("vnd_preco_total"));
                vendaConsulta.setFrete(rs.getDouble("vnd_frete"));
                vendaConsulta.setPagamentoAprovado(rs.getBoolean("vnd_pagamento_aprovado"));
                vendaConsulta.setVendaStatus(StatusVendaType.valueOf(rs.getString("vnd_status")));
                vendaConsulta.setDataCompra(rs.getTimestamp("vnd_dt_compra").toLocalDateTime().toLocalDate());
                vendaConsulta.setEnderecoEntrega((Endereco) enderecoDAO.listar(enderecoEntrega, "listarUnico").get(0));
                vendaConsulta.setEnderecoCobranca((Endereco) enderecoDAO.listar(enderecoCobranca, "listarUnico").get(0));

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
    private List<EntidadeDominio> criaVendaProvisoria(Venda venda) {
        Cliente cliente = venda.getCliente();

        defineEnderecosVenda(venda);

        List<CartaoDeCredito> cartoes = cartaoDeCreditoDAO.listar(new CartaoDeCredito(cliente), "listar").stream()
                .map(entidade -> (CartaoDeCredito) entidade).toList();

        List<Cupom> cupons = cupomDAO.listar(new Cupom(cliente), "listarTodos").stream()
                .map(entidade -> (Cupom) entidade).toList();

        venda.setCartoes(cartoes);
        venda.setCupons(cupons);

        return List.of(venda);
    }

    private void defineEnderecosVenda(Venda venda) {

        Endereco enderecoEntregaVenda = venda.getEnderecoEntrega();
        Endereco enderecoCobrancaVenda = venda.getEnderecoCobranca();
        Endereco enderecoEntrega = null;
        Endereco enderecoCobranca = null;

        List<Endereco> enderecos = enderecoDAO.listar(new Endereco(venda.getCliente()), "listar").stream()
                .map(entidade -> (Endereco) entidade).toList();


        if (enderecoEntregaVenda.getId() == null && enderecoCobrancaVenda.getId() == null){
            enderecoEntrega = enderecos.stream()
                    .filter(e -> e.getTipoEndereco().equals(EnderecoType.COBRANCA_ENTREGA))
                    .findFirst().orElse(null);

        } else if (enderecoEntregaVenda.getId() != null) {
            enderecoEntrega = (Endereco) enderecoDAO.listar(enderecoEntregaVenda, "listarUnico").get(0);
        }


        // Caso não tenha sido possível definir um endereço que sirva tanto para entrega quanto para cobrança
        if (!EnderecoType.COBRANCA_ENTREGA.equals(enderecoEntregaVenda.getTipoEndereco())){
            if (enderecoEntrega == null) {
                enderecoEntrega = enderecos.stream()
                        .filter(e -> e.getTipoEndereco().equals(EnderecoType.ENTREGA))
                        .findFirst().orElse(null);
            }

            if (enderecoCobrancaVenda.getId() == null) {
                enderecoCobranca = enderecos.stream()
                        .filter(e -> e.getTipoEndereco().equals(EnderecoType.COBRANCA))
                        .findFirst().orElse(null);
            } else
                enderecoCobranca = (Endereco) enderecoDAO.listar(enderecoCobrancaVenda, "listarUnico").get(0);
        }

        venda.setEnderecoEntrega(enderecoEntrega);
        venda.setEnderecoCobranca(enderecoCobranca);
    }

    private List<Cupom> getCuponsVenda(Cupom cupom, Venda venda) {
        List<Long> ids = venda.getCupons().stream()
                .map(Cupom::getId).toList();

        List<? extends EntidadeDominio> cupons = cupomDAO.listar(cupom, "listarTodos");

        return cupons.stream()
                .map(entidade -> (Cupom) entidade)
                .filter(c -> ids.contains(c.getId())).toList();
    }

}
