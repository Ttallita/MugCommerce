package model.carrinho;

import model.EntidadeDominio;
import model.produto.Produto;

public class ItemCompra extends EntidadeDominio {
    private Produto produto;
    private int quant;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuant() {
        return quant;
    }
    
    public void setQuant(int quant) {
        this.quant = quant;
    }
}
