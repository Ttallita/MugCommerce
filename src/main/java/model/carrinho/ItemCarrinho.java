package model.carrinho;

import model.produto.Produto;

public class ItemCarrinho {
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
