package model.carrinho;

import model.EntidadeDominio;
import model.produto.Produto;

import java.util.Objects;

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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemCarrinho that)) return false;
        return quant == that.quant
                && emTroca == that.emTroca
                && Objects.equals(produto, that.produto);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(produto, quant, emTroca);
    }

    @Override
    public String toString() {
        return "ItemCarrinho{" +
                "produto=" + produto +
                ", quant=" + quant +
                ", emTroca=" + emTroca +
                '}';
    }
}
