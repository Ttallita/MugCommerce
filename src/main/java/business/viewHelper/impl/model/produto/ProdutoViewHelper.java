package business.viewHelper.impl.model.produto;

import business.viewHelper.IViewHelper;
import com.google.gson.Gson;
import model.EntidadeDominio;
import model.Result;
import model.produto.Categoria;
import model.produto.Fabricante;
import model.produto.GrupoPrecificacao;
import model.produto.Produto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProdutoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        if ("salvar".equals(operacao)) {
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

            return produto;
        } else if(operacao.equals("listar")) {
            return new Produto();
        } else if("excluir".equals(operacao) || operacao.equals("listarUnico")) {
            Produto produto = new Produto();

            String id = request.getParameter("id");
            produto.setId(Long.parseLong(id));
            produto.setAtivo(false);

            return produto;
        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        String msgTela = result.getMsg();

        switch (operacao) {
            case "listar":
                request.setAttribute("produtos", result.getEntidades());
                request.getRequestDispatcher("/gerenciar/produtos.jsp").forward(request, response);
                break;
            case "salvar":
            case "excluir":
                if (msgTela == null)
                    response.sendRedirect("/emug/adm/produtos?operacao=listar");
                else {
                    String[] mensagens = msgTela.split("\n");

                    request.setAttribute("mensagens", mensagens);
                    request.setAttribute("erro", true);
                    request.getRequestDispatcher("/adm/formularios/formProduto.jsp").forward(request, response);
                }
                break;
            case "listarUnico":
                List<Produto> produtos = result.getEntidades()
                        .stream()
                        .map(entidade -> (Produto) entidade)
                        .toList();

                request.setAttribute("produto", produtos.get(0));
                request.setAttribute("categorias", new Gson().toJson(produtos.get(0).getCategorias()));
                request.getRequestDispatcher("/gerenciar/formularios/formProduto.jsp").forward(request, response);
                break;
        }
    }
}
