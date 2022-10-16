package model.carrinho;

import model.EntidadeDominio;
import model.produto.Produto;

public class ItemCarrinho extends EntidadeDominio {
    private Produto produto;
    private int quant;
    private boolean emTroca = false;

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

    public boolean isEmTroca() {
        return emTroca;
    }

    public void setEmTroca(boolean emTroca) {
        this.emTroca = emTroca;
    }
}
