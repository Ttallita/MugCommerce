package model.solicitacao;

import model.produto.Produto;

public class TrocaVenda extends SolicitacaoVendaAbstract{

    private Produto produto;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
