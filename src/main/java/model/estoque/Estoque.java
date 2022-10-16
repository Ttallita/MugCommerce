package model.estoque;

import model.EntidadeDominio;
import model.produto.Produto;

public class Estoque extends EntidadeDominio {
    private Produto produto;
    private int quantidade;


    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
