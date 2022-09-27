package model.produto;

import model.EntidadeDominio;

public class ProdutoStatus extends EntidadeDominio {
    private Produto produto;
    private CategoriaStatusType categoriaStatus;
    private String justificativa;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public CategoriaStatusType getCategoriaStatus() {
        return categoriaStatus;
    }

    public void setCategoriaStatus(CategoriaStatusType categoriaStatus) {
        this.categoriaStatus = categoriaStatus;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
}
