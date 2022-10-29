package business.strategy.impl.carrinho;

import business.strategy.IStrategy;
import dao.estoque.EstoqueDAO;
import model.EntidadeDominio;
import model.carrinho.ItemCarrinho;
import model.estoque.Estoque;

public class VerificaCarrinhoStrategy implements IStrategy {

    @Override
    public String processa(EntidadeDominio entidade) {
        ItemCarrinho item = (ItemCarrinho) entidade;

        Estoque estoque = new Estoque();
        estoque.setProduto(item.getProduto());

        Estoque estoqueConsultado = (Estoque) new EstoqueDAO()
                .listar(estoque, "findByIdProduto")
                .get(0);

        if(item.getQuant() > estoqueConsultado.getQuantidade()) {
            item.setQuant(item.getQuant() - 1);
            return "Não é possivel adicionar mais itens que o disponível no estoque.";
        }

        return null;
    }
}
