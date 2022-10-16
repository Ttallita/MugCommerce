package dao.cliente;

import dao.IDAO;
import model.EntidadeDominio;
import model.cliente.CartaoDeCredito;
import model.cliente.endereco.Endereco;
import model.cliente.endereco.EnderecoType;
import model.cupom.Cupom;
import model.cupom.CupomType;
import model.produto.Produto;
import utils.Conexao;
import utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CupomDAO implements IDAO {

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
                sql = "SELECT * FROM cupons c WHERE c.cpm_cli_usr_id = ? AND c.cpm_vnd_id IS NULL";

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
                cupomConsulta.setDataValidade(rs.getTimestamp("cpm_dt_validade").toLocalDateTime().toLocalDate());
                cupomConsulta.setDescricao(rs.getString("cpm_descricao"));

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
