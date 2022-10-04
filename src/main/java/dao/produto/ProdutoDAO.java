package dao.produto;

import dao.IDAO;
import model.EntidadeDominio;
import model.produto.Categoria;
import model.produto.Fabricante;
import model.produto.GrupoPrecificacao;
import model.produto.Produto;
import utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ProdutoDAO implements IDAO {

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        Produto produto = (Produto) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;
        try {
            connection = conexao.getConexao();

            String sql = "INSERT INTO produtos (pro_fab_id, pro_grp_id, pro_nome, pro_valor_compra, pro_valor_venda, pro_descricao, pro_material, pro_cod_barras, pro_imagem, pro_ativo)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            GrupoPrecificacao grupo  = (GrupoPrecificacao) new GrupoPrecificacaoDAO().listar(produto.getGrupoPrecificacao(), "listarUnico")
                    .get(0);

            double valorVenda = getValorVenda(produto, grupo);

            PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setLong(1, produto.getFabricante().getId());
            pstm.setLong(2, grupo.getId());
            setaValores(produto, valorVenda, pstm);
            pstm.setBoolean(10, produto.isAtivo());

            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();

            long idProduto = 0L;

            while (rs.next()) {
                idProduto = rs.getLong(1);
            }

            produto.setId(idProduto);

            long finalIdProduto = idProduto;
            Consumer<Categoria> consumer = (categoria) -> new CategoriaDAO().salvarAssociacaoCategoriaProduto(finalIdProduto, categoria.getId());
            produto.getCategorias().forEach(consumer);

            return produto;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return null;
    }

    private static double getValorVenda(Produto produto, GrupoPrecificacao grupo) {
        return produto.getValorCompra() + (produto.getValorCompra() + (grupo.getMargemLucro() / 100));
    }

    @Override
    public EntidadeDominio atualizar(EntidadeDominio entidade) {
        Produto produto = (Produto) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.getConexao();

            String sql = "UPDATE produtos SET pro_fab_id = ?, pro_grp_id = ?, pro_nome = ?, pro_valor_compra = ?" +
                    ", pro_valor_venda = ?, pro_descricao = ?, pro_material = ?, pro_cod_barras = ?, pro_imagem = ?" +
                    " WHERE pro_id = ?;";

            GrupoPrecificacao grupo  = (GrupoPrecificacao) new GrupoPrecificacaoDAO().listar(produto.getGrupoPrecificacao(), "listarUnico")
                    .get(0);

            double valorVenda = getValorVenda(produto, grupo);

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, produto.getFabricante().getId());
            pstm.setLong(2, produto.getGrupoPrecificacao().getId());
            setaValores(produto, valorVenda, pstm);
            pstm.setLong(10, produto.getId());
            pstm.execute();

            return produto;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(conn);
        }

        return null;
    }

    public void setaValores(Produto produto, double valorVenda, PreparedStatement pstm) throws SQLException {
        pstm.setString(3, produto.getNome());
        pstm.setDouble(4, produto.getValorCompra());
        pstm.setDouble(5, valorVenda);
        pstm.setString(6, produto.getDescricao());
        pstm.setString(7, produto.getMaterial());
        pstm.setString(8, produto.getCodBarras());
        pstm.setString(9, produto.getImagem());
    }

    @Override
    public EntidadeDominio deletar(EntidadeDominio entidade) {
        Produto produto = (Produto) entidade;

        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.getConexao();

            String sql = "UPDATE produtos SET pro_ativo = ? WHERE pro_id = ?;";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setBoolean(1, produto.isAtivo());
            pstm.setLong(2, produto.getId());

            pstm.execute();

            return produto;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(conn);
        }

        return null;
    }


    @Override
    public List<EntidadeDominio> listar(EntidadeDominio entidade, String operacao) {
        Produto produto = (Produto) entidade;

        Conexao conexao = new Conexao();
        Connection connection = null;
        try {
            connection = conexao.getConexao();

            String sql;
            PreparedStatement pstm = null;

            if(operacao.equals("listar")) {
                sql = "SELECT * FROM produtos";

                pstm = connection.prepareStatement(sql);

            } if(operacao.equals("pesquisar")) {
                sql = "SELECT * FROM produtos WHERE pro_ativo = true";

                pstm = connection.prepareStatement(sql);
            } else if(operacao.equals("listarUnico")) {
                sql = "SELECT * FROM produtos where pro_id = ?";

                pstm = connection.prepareStatement(sql);
                pstm.setLong(1, produto.getId());
            }

            ResultSet rs = pstm.executeQuery();

            List<EntidadeDominio> produtos = new ArrayList<>();
            while (rs.next()) {
                Produto produtoConsulta = new Produto();
                produtoConsulta.setId(rs.getLong("pro_id"));

                Fabricante fabricante = (Fabricante) new FabricanteDAO()
                        .listar(new Fabricante(rs.getLong("pro_fab_id"), ""), "listarUnico")
                        .get(0);

                produtoConsulta.setFabricante(fabricante);

                GrupoPrecificacao grupo = (GrupoPrecificacao) new GrupoPrecificacaoDAO()
                        .listar(new GrupoPrecificacao(rs.getLong("pro_grp_id"), ""), "listarUnico")
                        .get(0);

                produtoConsulta.setGrupoPrecificacao(grupo);

                produtoConsulta.setId(rs.getLong("pro_id"));
                produtoConsulta.setNome(rs.getString("pro_nome"));
                produtoConsulta.setValorCompra(rs.getDouble("pro_valor_compra"));
                produtoConsulta.setValorVenda(rs.getDouble("pro_valor_venda"));
                produtoConsulta.setDescricao(rs.getString("pro_descricao"));
                produtoConsulta.setMaterial(rs.getString("pro_material"));
                produtoConsulta.setCodBarras(rs.getString("pro_cod_barras"));
                produtoConsulta.setImagem(rs.getString("pro_imagem"));

                List<Categoria> categorias = new CategoriaDAO()
                        .listar(produtoConsulta, "findByProduto")
                        .stream()
                        .map(categoria -> (Categoria) categoria)
                        .toList();

                produtoConsulta.setCategorias(categorias);
                produtoConsulta.setAtivo(rs.getBoolean("pro_ativo"));
                produtos.add(produtoConsulta);
            }

            System.err.println("Listando " + produtos.size() + " produto(s)");

            return produtos;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            conexao.fecharConexao(connection);
        }

        return null;
    }
}
