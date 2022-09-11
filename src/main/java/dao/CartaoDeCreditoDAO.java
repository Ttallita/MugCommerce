package dao;

import model.EntidadeDominio;
import model.cliente.CartaoDeCredito;
import model.cliente.endereco.Endereco;
import utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaoDeCreditoDAO implements IDAO{

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        CartaoDeCredito cartao = (CartaoDeCredito) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;
        try {
            conn = conexao.getConexao();

            String sql = "INSERT INTO cartoes (crt_cli_usr_id, crt_numero, crt_bandeira, crt_nome_impresso, crt_mes_validade," +
                    " crt_ano_validade, crt_cod_seg, crt_preferencial)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setLong(1, cartao.getCliente().getUsuario().getId());
            pstm.setString(2, cartao.getNumCartao());
            pstm.setString(3, cartao.getBandeira());
            pstm.setString(4, cartao.getNomeImpressoCartao());
            pstm.setInt(5, cartao.getMesValidade());
            pstm.setInt(6, cartao.getAnoValidade());
            pstm.setInt(7, cartao.getCodigo());
            pstm.setBoolean(8, cartao.isPreferencial());
            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();

            long cartaoId = 0L;

            while (rs.next()) {
                cartaoId = rs.getLong(1);
            }

            cartao.setId(cartaoId);

            return cartao;
        }catch (SQLException | ClassNotFoundException e) {
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

            String sql = "UPDATE cartoes SET crt_numero = ?, crt_bandeira = ?, crt_nome_impresso = ?, crt_mes_validade = ?," +
                    " crt_ano_validade = ?, crt_cod_seg = ? WHERE crt_id = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, cartao.getNumCartao());
            pstm.setString(2, cartao.getBandeira());
            pstm.setString(3, cartao.getNomeImpressoCartao());
            pstm.setInt(4, cartao.getMesValidade());
            pstm.setInt(5, cartao.getAnoValidade());
            pstm.setInt(6, cartao.getCodigo());
            pstm.setLong(7, cartao.getId());

            pstm.execute();

            return cartao;
        }catch (SQLException | ClassNotFoundException e) {
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

            String sql = "DELETE FROM cartoes where crt_id = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);;
            pstm.setLong(1, cartao.getId());

            pstm.execute();

            return cartao;
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
        CartaoDeCredito cartao = (CartaoDeCredito) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;
        try {
            conn = conexao.getConexao();

            String sql;
            PreparedStatement pstm = null;

            if(operacao.equals("listar")) {
                sql = "SELECT * FROM cartoes where crt_cli_usr_id = ?";

                pstm = conn.prepareStatement(sql);
                pstm.setLong(1,cartao.getCliente().getUsuario().getId());
            } else if(operacao.equals("listarUnico")) {
                sql = "SELECT * FROM cartoes where crt_cli_usr_id = ? AND crt_id = ?";

                pstm = conn.prepareStatement(sql);
                pstm.setLong(1, cartao.getCliente().getUsuario().getId());
                pstm.setLong(2, cartao.getId());
            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> cartoes = new ArrayList<>();
            while (rs.next()) {
                CartaoDeCredito cartaoConsulta = new CartaoDeCredito();
                cartaoConsulta.setId(rs.getLong(1));
                cartaoConsulta.setCliente(cartao.getCliente());
                cartaoConsulta.setNumCartao(rs.getString(3));
                cartaoConsulta.setBandeira( rs.getString(4));
                cartaoConsulta.setNomeImpressoCartao(rs.getString(5));
                cartaoConsulta.setMesValidade(rs.getInt(6));
                cartaoConsulta.setAnoValidade(rs.getInt(7));
                cartaoConsulta.setCodigo(rs.getInt(8));

                cartoes.add(cartaoConsulta);
            }

            return cartoes;
        }catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }finally {
            conexao.fecharConexao(conn);
        }

        return null;
    }
}
