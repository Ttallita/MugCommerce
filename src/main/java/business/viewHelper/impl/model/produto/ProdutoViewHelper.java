package business.viewHelper.impl.model.produto;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.produto.Fabricante;
import model.produto.GrupoPrecificacao;
import model.produto.Produto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;

public class ProdutoViewHelper implements IViewHelper {

    private static final String UPLOAD_DIRECTORY = "/produto";

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        if ("salvar".equals(operacao)) {
            Produto produto = new Produto();
            produto.setNome(request.getParameter("nomeProduto"));
            produto.setImagem(request.getParameter("imageBase64"));

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

            return produto;
        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
