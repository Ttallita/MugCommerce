package business.viewHelper.impl.model.produto;

import business.viewHelper.IViewHelper;
import com.google.gson.Gson;
import dao.AuditoriaDAO;
import model.AuditoriaType;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.produto.Categoria;
import model.produto.Fabricante;
import model.produto.GrupoPrecificacao;
import model.produto.Produto;
import utils.Utils;
import model.produto.*;
import utils.UtilsWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        if ("salvar".equals(operacao)) {
            return criaProdutoBasico(request);
        } else if(operacao.equals("listar") || operacao.equals("listarIndex") || operacao.equals("pesquisar")) {
            return new Produto();
        } else if(operacao.equals("listarUnico")) {
            Produto produto = new Produto();

            String id = request.getParameter("id");
            produto.setId(Long.parseLong(id));
            produto.setAtivo(false);

            return produto;
        } else if("excluir".equals(operacao)) {
            ProdutoStatus status = new ProdutoStatus();

            String id = request.getParameter("id");
            Produto produto = new Produto();
            produto.setId(Long.parseLong(id));

            String statusProduto = request.getParameter("status");
            produto.setAtivo(statusProduto != null);

            status.setProduto(produto);
            status.setCategoriaStatus(CategoriaStatusType.valueOf(request.getParameter("categoriaInativacao")));
            status.setJustificativa(request.getParameter("justificativa"));

            return status;
        } else if(operacao.equals("atualizar")) {
            Produto produto = criaProdutoBasico(request);
            produto.setId(Long.valueOf(request.getParameter("id")));

            return produto;
        }

        return null;
    }

    private static Produto criaProdutoBasico(HttpServletRequest request) {
        Produto produto = new Produto();
        produto.setNome(request.getParameter("nomeProduto"));
        produto.setImagem(request.getParameter("imagemBase64"));

        String valorCompra = request.getParameter("valorCompra");

        if(valorCompra != null) {
            String valorCompraFormatado = valorCompra.replace(".", "")
                    .replace(",", ".");
            produto.setValorCompra(Double.parseDouble(valorCompraFormatado));
        }

        produto.setCodBarras(request.getParameter("codBarras"));
        produto.setMaterial(request.getParameter("material"));

        if(request.getParameter("fabricante") != null) {
            long idFabricante = Long.parseLong(request.getParameter("fabricante"));
            produto.setFabricante(new Fabricante(idFabricante, ""));
        }

        if(request.getParameter("grupoPrecificacao") != null) {
            long idGrupoPrecificacao = Long.parseLong(request.getParameter("grupoPrecificacao"));
            produto.setGrupoPrecificacao(new GrupoPrecificacao(idGrupoPrecificacao, ""));
        }

        produto.setDescricao(request.getParameter("descricao"));

        if(request.getParameter("categorias") != null) {
            List<Categoria> categorias = Arrays.stream(request.getParameterValues("categorias"))
                    .map(categoria -> new Categoria(Long.parseLong(categoria), ""))
                    .toList();

            produto.setCategorias(categorias);
        }

        produto.setAtivo(request.getParameter("isAtivo").equals("on"));
        return produto;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");
        String msgTela = result.getMsg();

        switch (operacao) {

            case "listar" -> {
                List<CategoriaStatusType> categoriasInativacao = Arrays.stream(CategoriaStatusType.values())
                        .filter(categoriaStatusType -> !categoriaStatusType.getType().equals(StatusType.ATIVO))
                        .toList();
                request.setAttribute("categoriasInativacao", categoriasInativacao);
                request.setAttribute("produtos", result.getEntidades());
                request.getRequestDispatcher("/gerenciar/produtos.jsp").forward(request, response);
            }

            case "listarIndex" ->
                UtilsWeb.montaRespostaJson(result, request, response);

            case "pesquisar" -> {
                request.setAttribute("produtos", result.getEntidades());
                request.getRequestDispatcher("/pesquisa.jsp").forward(request, response);
            }

            case "salvar", "excluir", "atualizar" -> {
                Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
                if (msgTela == null) {
                    AuditoriaType tipo = operacao.equals("salvar") ? AuditoriaType.INSERCAO : AuditoriaType.ALTERACAO;

                    new AuditoriaDAO().salvar(Utils.criaAuditoria(result.getEntidades().get(0), tipo, usuarioLogado));
                    response.sendRedirect("/emug/adm/produtos?operacao=listar");
                } else {
                    String[] mensagens = msgTela.split("\n");

                    request.setAttribute("mensagens", mensagens);
                    request.setAttribute("erro", true);
                    request.getRequestDispatcher("/adm/formularios/formProduto.jsp").forward(request, response);
                }
            }

            case "listarUnico" -> {
                List<Produto> produtos = result.getEntidades()
                        .stream()
                        .map(entidade -> (Produto) entidade)
                        .toList();

                List<CategoriaStatusType> categoriasAtivacao = Arrays.stream(CategoriaStatusType.values())
                        .filter(categoriaStatusType -> !categoriaStatusType.getType().equals(StatusType.INATIVO))
                        .toList();

                request.setAttribute("categoriasAtivacao", categoriasAtivacao);
                request.setAttribute("produto", produtos.get(0));
                request.setAttribute("categorias", new Gson().toJson(produtos.get(0).getCategorias()));

                if (request.getRequestURI().contains("adm"))
                    request.getRequestDispatcher("/gerenciar/formularios/formProduto.jsp").forward(request, response);
                else
                    request.getRequestDispatcher("/produto.jsp").forward(request, response);
            }
        }
    }
}
