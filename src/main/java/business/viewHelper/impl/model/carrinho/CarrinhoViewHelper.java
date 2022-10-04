package business.viewHelper.impl.model.carrinho;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.carrinho.ItemCompra;
import model.produto.Produto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CarrinhoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        if(operacao.equals("salvar")) {
            Produto produto = new Produto();
            produto.setId(Long.parseLong(request.getParameter("id")));

            ItemCompra itemCarrinho = new ItemCompra();
            itemCarrinho.setProduto(produto);
            itemCarrinho.setQuant(1);

            return itemCarrinho;
        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        if(operacao.equals("salvar")) {
            response.sendRedirect("/emug/cliente/carrinho.jsp");
        }
    }
}
