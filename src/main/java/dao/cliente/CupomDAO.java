package dao.cliente;

import dao.IDAO;
import model.EntidadeDominio;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import model.cupom.Cupom;
import model.cupom.CupomType;
import utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CupomDAO implements IDAO {

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        Cupom cupom = (Cupom) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;
        try {
            conn = conexao.getConexao();

            String sql = "INSERT INTO cupons (cpm_cli_usr_id, cpm_nome, cpm_tp, cpm_valor, cpm_dt_validade, cpm_descricao) " +
                    " VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setLong(1, cupom.getCliente().getUsuario().getId());
            pstm.setString(2, cupom.getNome());
            pstm.setString(3, cupom.getTipo().name());
            pstm.setDouble(4, cupom.getValor());
            pstm.setObject(5, cupom.getDataValidade());
            pstm.setString(6, cupom.getDescricao());

            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();

            while (rs.next()) {
                cupom.setId(rs.getLong(1));;
            }

            return cupom;
        }catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }finally {
            conexao.fecharConexao(conn);
        }
    }

    @Override
    public EntidadeDominio atualizar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio deletar(EntidadeDominio entidade) {
        Cupom cupom = (Cupom) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.getConexao();

            String sql = "DELETE FROM cupons WHERE cpm_id = ?;";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, cupom.getId());

            pstm.execute();

            return cupom;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(conn);
        }

        return null;
    }

    @Override
    public List<EntidadeDominio> listar(EntidadeDominio entidade, String operacao) {
        Cupom cupom = (Cupom) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.getConexao();

            String sql;
            PreparedStatement pstm = null;

            if(operacao.equals("listarTodos")) { // cupons válidos para venda
                sql = "SELECT * FROM cupons c WHERE c.cpm_cli_usr_id = ?";

                pstm = conn.prepareStatement(sql);
                pstm.setLong(1, cupom.getCliente().getUsuario().getId());
            }

            if(operacao.equals("listarUnico")) {
                sql = "SELECT * FROM cupons c WHERE c.cpm_cli_usr_id = ? AND c.cpm_id = ?";

                pstm = conn.prepareStatement(sql);
                pstm.setLong(1, cupom.getCliente().getUsuario().getId());
                pstm.setLong(2, cupom.getId());
            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> cartoes = new ArrayList<>();
            while (rs.next()) {
                Cupom cupomConsulta = new Cupom();
                cupomConsulta.setCliente(cupom.getCliente());

                cupomConsulta.setId(rs.getLong("cpm_id"));
                cupomConsulta.setNome(rs.getString("cpm_nome"));
                cupomConsulta.setTipo(CupomType.valueOf(rs.getString("cpm_tp")));
                cupomConsulta.setValor(rs.getDouble("cpm_valor"));
                cupomConsulta.setDescricao(rs.getString("cpm_descricao"));

                Timestamp dataValidade = rs.getTimestamp("cpm_dt_validade");
                cupomConsulta.setDataValidade(dataValidade != null ? dataValidade.toLocalDateTime().toLocalDate() : null);

                cartoes.add(cupomConsulta);
            }

            return cartoes;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(conn);
        }

        return null;
    }

}
