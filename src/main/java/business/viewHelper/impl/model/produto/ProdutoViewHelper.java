package business.viewHelper.impl.model.produto;

import business.viewHelper.IViewHelper;
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
import java.util.stream.Collectors;

public class ProdutoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        if ("salvar".equals(operacao)) {
            Produto produto = new Produto();
            produto.setNome(request.getParameter("nomeProduto"));
            produto.setImagem(request.getParameter("imagemBase64"));

            String valorCompraFormatado = request.getParameter("valorCompra")
                    .replace(".", "")
                    .replace(",", ".");

            produto.setValorCompra(Double.parseDouble(valorCompraFormatado));
            produto.setCodBarras(request.getParameter("codBarras"));
            produto.setMaterial(request.getParameter("material"));

            long idFabricante = Long.parseLong(request.getParameter("fabricante"));
            produto.setFabricante(new Fabricante(idFabricante, ""));

            long idGrupoPrecificacao = Long.parseLong(request.getParameter("grupoPrecificacao"));
            produto.setGrupoPrecificacao(new GrupoPrecificacao(idGrupoPrecificacao, ""));
            produto.setDescricao(request.getParameter("descricao"));

            List<Categoria> categorias = Arrays.stream(request.getParameterValues("categorias"))
                    .map(categoria -> new Categoria(Long.parseLong(categoria), ""))
                    .toList();

            produto.setCategorias(categorias);

            return produto;
        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // TODO precisa fazer uma tela dedicada para os produtos
    }
}
