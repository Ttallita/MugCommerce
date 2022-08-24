package model.produto;

import model.EntidadeDominio;

import java.time.LocalDate;
import java.util.List;

public class Produto extends EntidadeDominio {
    private String nome;
    private Double valorCompra;
    private Double valorVenda;
    private String descricao;
    private String material;
    private GrupoPrecificacao grupoPrecificacao;
    private int limiteVenda;
    private int quantEstoque;
    private Fabricante fabricante;
    private String codBarras;
    private LocalDate dtEntradaEstoque;
    private List<Categoria> categorias;
    private boolean limitado;
    private Status status;
    private String link;

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

    public int getLimiteVenda() {
        return limiteVenda;
    }

    public void setLimiteVenda(int limiteVenda) {
        this.limiteVenda = limiteVenda;
    }

    public int getQuantEstoque() {
        return quantEstoque;
    }

    public void setQuantEstoque(int quantEstoque) {
        this.quantEstoque = quantEstoque;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public boolean getLimitado(){
        return limitado;
    }

    public void setLimitado(boolean limitado){
        this.limitado = limitado;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public LocalDate getDtEntradaEstoque() {
        return dtEntradaEstoque;
    }

    public void setDtEntradaEstoque(LocalDate dtEntradaEstoque) {
        this.dtEntradaEstoque = dtEntradaEstoque;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
