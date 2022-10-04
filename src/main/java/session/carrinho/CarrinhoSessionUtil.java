package session.carrinho;

import dao.produto.ProdutoDAO;
import model.EntidadeDominio;
import model.carrinho.Carrinho;
import model.carrinho.ItemCompra;
import model.produto.Produto;

import javax.servlet.http.HttpSession;

public class CarrinhoSessionUtil implements session.ISessionUtil {

    @Override
    public void salvar(EntidadeDominio entidade, HttpSession session) {
        ItemCompra item = (ItemCompra) entidade;

        Produto produto = (Produto) new ProdutoDAO()
                .listar(item.getProduto(), "listarUnico")
                .get(0);

        item.setProduto(produto);

        Carrinho carrinho;

        if(session.getAttribute("carrinho") == null)
            session.setAttribute("carrinho", new Carrinho());

        carrinho = (Carrinho) session.getAttribute("carrinho");

        boolean existeProduto = carrinho.getItensCarrinho()
                .stream()
                .anyMatch(itemCarrinho -> itemCarrinho.getProduto().equals(item.getProduto()));

        if(!existeProduto)
            carrinho.addItem(item);
    }

    @Override
    public void atualizar(EntidadeDominio entidade, HttpSession session) {
        ItemCarrinho item = (ItemCarrinho) entidade;


        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

        for (ItemCarrinho itemCarrinho : carrinho.getItensCarrinho()) {
            if(item.getProduto().getId().equals(itemCarrinho.getProduto().getId()))
                itemCarrinho.setQuant(item.getQuant());

        }
    }

    @Override
    public void remover(EntidadeDominio entidade, HttpSession session) {
        ItemCarrinho item = (ItemCarrinho) entidade;

        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

        carrinho.removeItem(item);
    }

    @Override
    public void listar(EntidadeDominio entidade, HttpSession session, String operacao) {

    }
}
