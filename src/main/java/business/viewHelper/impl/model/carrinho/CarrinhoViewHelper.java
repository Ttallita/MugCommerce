package business.viewHelper.impl.model.carrinho;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.carrinho.Carrinho;
import model.carrinho.ItemCarrinho;
import model.cliente.Cliente;
import model.produto.Produto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        if(operacao.equals("salvar")) {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);

            Produto produto = new Produto();
            produto.setId(Long.parseLong(request.getParameter("id")));

            ItemCarrinho itemCarrinho = new ItemCarrinho();
            itemCarrinho.setProduto(produto);
            itemCarrinho.setQuant(1);

            List<ItemCarrinho> itensCarrinhos = new ArrayList<>();
            itensCarrinhos.add(itemCarrinho);

            Carrinho carrinho = new Carrinho();
            carrinho.setCliente(cliente);
            carrinho.setItensCarrinho(itensCarrinhos);

            return carrinho;
        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
