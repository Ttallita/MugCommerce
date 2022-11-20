package model.produto;

import model.EntidadeDominio;

import java.util.List;
import java.util.Objects;

public class Produto extends EntidadeDominio {
    private String nome;
    private Double valorCompra;
    private Double valorVenda;
    private String descricao;
    private String material;
    private GrupoPrecificacao grupoPrecificacao;
    private Fabricante fabricante;
    private String codBarras;
    private List<Categoria> categorias;
    private String imagem;

    private boolean emTroca = false;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public GrupoPrecificacao getGrupoPrecificacao() {
        return grupoPrecificacao;
    }

    public void setGrupoPrecificacao(GrupoPrecificacao grupoPrecificacao) {
        this.grupoPrecificacao = grupoPrecificacao;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public boolean isEmTroca() {
        return emTroca;
    }

    public void setEmTroca(boolean emTroca) {
        this.emTroca = emTroca;
    }

    protected boolean canEqual(Object obj) {
        return (obj instanceof Produto);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;
        return emTroca == produto.emTroca
                && Objects.equals(nome, produto.nome)
                && Objects.equals(valorCompra, produto.valorCompra)
                && Objects.equals(valorVenda, produto.valorVenda)
                && Objects.equals(descricao, produto.descricao)
                && Objects.equals(material, produto.material)
                && Objects.equals(grupoPrecificacao, produto.grupoPrecificacao)
                && Objects.equals(fabricante, produto.fabricante)
                && Objects.equals(codBarras, produto.codBarras)
                && Objects.equals(categorias, produto.categorias)
                && Objects.equals(imagem, produto.imagem)
                && produto.canEqual(this);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(nome, valorCompra, valorVenda, descricao, material, grupoPrecificacao, fabricante, codBarras, categorias, imagem, emTroca);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", valorCompra=" + valorCompra +
                ", valorVenda=" + valorVenda +
                ", descricao='" + descricao + '\'' +
                ", material='" + material + '\'' +
                ", grupoPrecificacao=" + grupoPrecificacao +
                ", fabricante=" + fabricante +
                ", codBarras='" + codBarras + '\'' +
                ", categorias=" + categorias +
                ", imagem='" + imagem + '\'' +
                '}';
    }
}
