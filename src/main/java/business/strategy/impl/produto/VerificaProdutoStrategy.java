package business.strategy.impl.produto;

import business.strategy.IStrategy;
import model.EntidadeDominio;
import model.produto.Produto;

public class VerificaProdutoStrategy implements IStrategy {

    @Override
    public String processa(EntidadeDominio entidade) {
        Produto produto = (Produto) entidade;

        if(produto.getNome().isBlank())
            return "Insira um nome para o produto";

        if(produto.getValorCompra() == null)
            return "Insira o valor de compra do produto";

        if(produto.getDescricao().isBlank())
            return "Insira uma descrição para o produto";

        if(produto.getMaterial().isBlank())
            return "Insira o material do produto";

        if(produto.getGrupoPrecificacao() == null)
            return "Escolha um grupo de precificação";

        if(produto.getFabricante() == null)
            return "Escolha um fabricantes";

        if(produto.getCodBarras().isBlank())
            return "Digite o código de barras";

        if(produto.getCategorias() == null)
            return "Escolha uma ou mais categorias para o produto";

        if(produto.getImagem().isBlank())
            return "Insira uma imagem para o produto";

        return null;
    }
}
