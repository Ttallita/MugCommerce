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

        switch (operacao) {
            case "salvar" -> {
                Produto produto = new Produto();
                produto.setId(Long.parseLong(request.getParameter("id")));

                ItemCompra itemCarrinho = new ItemCompra();
                itemCarrinho.setProduto(produto);
                itemCarrinho.setQuant(1);

                return itemCarrinho;
            }
            case "excluir" -> {
                ItemCompra item = new ItemCompra();

                Produto produto = new Produto();
                produto.setId(Long.parseLong(request.getParameter("id")));

                item.setProduto(produto);

                return item;
            }
            case "atualizar" -> {
                Produto produto = new Produto();
                produto.setId(Long.parseLong(request.getParameter("id")));


                String tipo = request.getParameter("tipo");
                int quantidade = Integer.parseInt(request.getParameter("quantidade"));

                if (tipo.equals("adicionar"))
                    quantidade++;
                else
                    quantidade--;


                ItemCompra itemCarrinho = new ItemCompra();
                itemCarrinho.setProduto(produto);
                itemCarrinho.setQuant(quantidade);

                return itemCarrinho;
            }
        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect("/emug/cliente/carrinho.jsp");
    }
}
