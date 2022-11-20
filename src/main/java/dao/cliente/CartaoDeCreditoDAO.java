package dao.cliente;

import dao.IDAO;
import model.EntidadeDominio;
import model.Usuario;
import model.cliente.CartaoDeCredito;
import model.cliente.Cliente;
import org.checkerframework.checker.units.qual.C;
import utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaoDeCreditoDAO implements IDAO {

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        CartaoDeCredito cartao = (CartaoDeCredito) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;
        try {
            conn = conexao.getConexao();

            if(cartao.isPreferencial())
                atualizaCartaoPreferencial(cartao);

            String sql = "INSERT INTO cartoes (crt_cli_usr_id, crt_numero, crt_bandeira, crt_nome_impresso, crt_mes_validade," +
                    " crt_ano_validade, crt_cod_seg, crt_preferencial, crt_ativo)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setLong(1, cartao.getCliente().getUsuario().getId());
            pstm.setString(2, cartao.getNumCartao());
            pstm.setString(3, cartao.getBandeira());
            pstm.setString(4, cartao.getNomeImpressoCartao());
            pstm.setString(5, cartao.getMesValidade());
            pstm.setString(6, cartao.getAnoValidade());
            pstm.setInt(7, cartao.getCodigo());
            pstm.setBoolean(8, !existeCartaoPreferencial(cartao.getCliente().getUsuario()) || cartao.isPreferencial());
            pstm.setBoolean(9, true);
            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();

            long cartaoId = 0L;

            while (rs.next()) {
                cartaoId = rs.getLong(1);
            }

            cartao.setId(cartaoId);

            return cartao;
        }catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }finally {
            conexao.fecharConexao(conn);
        }

        return null;
    }

    @Override
    public EntidadeDominio atualizar(EntidadeDominio entidade) {
        CartaoDeCredito cartao = (CartaoDeCredito) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;
        try {
            conn = conexao.getConexao();

            if(cartao.isPreferencial())
                atualizaCartaoPreferencial(cartao);

            String sql = "UPDATE cartoes SET crt_numero = ?, crt_bandeira = ?, crt_nome_impresso = ?, crt_mes_validade = ?," +
                    " crt_ano_validade = ?, crt_cod_seg = ?, crt_preferencial = ? WHERE crt_id = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, cartao.getNumCartao());
            pstm.setString(2, cartao.getBandeira());
            pstm.setString(3, cartao.getNomeImpressoCartao());
            pstm.setString(4, cartao.getMesValidade());
            pstm.setString(5, cartao.getAnoValidade());
            pstm.setInt(6, cartao.getCodigo());

            pstm.setBoolean(7, !existeCartaoPreferencial(cartao.getCliente().getUsuario()) || cartao.isPreferencial());
            pstm.setLong(8, cartao.getId());

            pstm.execute();

            return cartao;
        }catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }finally {
            conexao.fecharConexao(conn);
        }

    }

    @Override
    public EntidadeDominio deletar(EntidadeDominio entidade) {
        CartaoDeCredito cartao = (CartaoDeCredito) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;
        try {
            conn = conexao.getConexao();

            String sql = "SELECT true FROM cartoes_em_venda WHERE crv_crt_id = ? LIMIT 1";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, cartao.getId());

            ResultSet rs = pstm.executeQuery();

            boolean isEmVenda = false;
            while (rs.next()) {
                isEmVenda = rs.getBoolean("isEmVenda");
            }

            if (isEmVenda)
                sql = "UPDATE cartoes SET crt_ativo = false WHERE crt_id = ?";
            else
                sql = "DELETE FROM cartoes where crt_id = ?";

            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, cartao.getId());

            pstm.execute();

            return cartao;
        }catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }finally {
            conexao.fecharConexao(conn);
        }

        return null;
    }

    @Override
    public List<EntidadeDominio> listar(EntidadeDominio entidade, String operacao) {
        CartaoDeCredito cartao = (CartaoDeCredito) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;
        try {
            conn = conexao.getConexao();

            String sql;
            PreparedStatement pstm = null;

            switch (operacao) {
                case "listar", "listarJson" -> {
                    sql = "SELECT * FROM cartoes where crt_cli_usr_id = ?";
                    pstm = conn.prepareStatement(sql);
                    pstm.setLong(1, cartao.getCliente().getUsuario().getId());
                }
                case "listarUnico" -> {
                    sql = "SELECT * FROM cartoes where crt_cli_usr_id = ? AND crt_id = ?";
                    pstm = conn.prepareStatement(sql);
                    pstm.setLong(1, cartao.getCliente().getUsuario().getId());
                    pstm.setLong(2, cartao.getId());
                }
                case "findCartaoPreferencial" -> {
                    sql = "SELECT * FROM cartoes where crt_preferencial = ? AND crt_cli_usr_id = ?";
                    pstm = conn.prepareStatement(sql);
                    pstm.setBoolean(1, true);
                    pstm.setLong(2, cartao.getCliente().getUsuario().getId());
                }
            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> cartoes = new ArrayList<>();
            while (rs.next()) {
                CartaoDeCredito cartaoConsulta = new CartaoDeCredito();
                cartaoConsulta.setId(rs.getLong("crt_id"));
                cartaoConsulta.setCliente(cartao.getCliente());
                cartaoConsulta.setNumCartao(rs.getString("crt_numero"));
                cartaoConsulta.setBandeira( rs.getString("crt_bandeira"));
                cartaoConsulta.setNomeImpressoCartao(rs.getString("crt_nome_impresso"));
                cartaoConsulta.setMesValidade(rs.getString("crt_mes_validade"));
                cartaoConsulta.setAnoValidade(rs.getString("crt_ano_validade"));
                cartaoConsulta.setCodigo(rs.getInt("crt_cod_seg"));
                cartaoConsulta.setPreferencial(rs.getBoolean("crt_preferencial"));

                cartoes.add(cartaoConsulta);
            }

            return cartoes;
        }catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }finally {
            conexao.fecharConexao(conn);
        }

        return null;
    }

    public void atualizaCartaoPreferencial(CartaoDeCredito cartao) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        try {
            conn = conexao.getConexao();

            String sql = "UPDATE cartoes SET crt_preferencial = ? WHERE crt_preferencial = ? AND crt_cli_usr_id = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setBoolean(1, false);
            pstm.setBoolean(2, true);
            pstm.setLong(3, cartao.getCliente().getUsuario().getId());

            pstm.execute();
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }finally {
            conexao.fecharConexao(conn);
        }

    }

    public boolean existeCartaoPreferencial(Usuario usuario) {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito();
        cartaoDeCredito.setCliente(new Cliente(usuario));

        return listar(cartaoDeCredito, "findCartaoPreferencial").size() > 0;
    }
}
