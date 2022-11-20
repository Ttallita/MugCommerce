package dao.dashboard;

import dao.IDAO;
import dao.cliente.CartaoDeCreditoDAO;
import dao.cliente.ClienteDAO;
import dao.cliente.CupomDAO;
import dao.cliente.EnderecoDAO;
import dao.estoque.EstoqueDAO;
import model.EntidadeDominio;
import model.Usuario;
import model.carrinho.Carrinho;
import model.carrinho.ItemCarrinho;
import model.cliente.CartaoDeCredito;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import model.cliente.endereco.EnderecoType;
import model.cupom.Cupom;
import model.cupom.CupomType;
import model.estoque.Estoque;
import model.produto.Produto;
import model.venda.DashboardVendasAgrupadas;
import model.venda.StatusVendaType;
import model.venda.Venda;
import utils.Conexao;
import utils.Utils;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAO implements IDAO {

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
        DashboardVendasAgrupadas vendasAgrupadas = (DashboardVendasAgrupadas) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;

        try {
            connection = conexao.getConexao();

            String sql = "select vnd_dt_compra, produtos.pro_nome, sum(pev.prv_quant) as quant" +
                            "    from vendas v " +
                            "    inner join produtos_em_venda pev on vnd_id = pev.prv_vnd_id " +
                            "    inner join produtos on pev.prv_pro_id  = pro_id" +
                            "    where vnd_dt_compra >= ? and vnd_dt_compra <= ?" +
                            "    group by vnd_dt_compra, produtos.pro_nome" +
                            "    order by vnd_dt_compra ;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, vendasAgrupadas.getDataInicio());
            pstm.setObject(2, vendasAgrupadas.getDataFim());

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> vendas = new ArrayList<>();
            while (rs.next()) {
                DashboardVendasAgrupadas vendaConsulta = new DashboardVendasAgrupadas();
                vendaConsulta.setDataCompra(rs.getTimestamp("vnd_dt_compra").toLocalDateTime().toLocalDate());
                vendaConsulta.setNome(rs.getString("pro_nome"));
                vendaConsulta.setQuantidade(rs.getInt("quant"));

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
}

