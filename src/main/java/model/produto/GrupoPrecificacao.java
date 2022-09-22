package model.produto;

import model.EntidadeDominio;

public class GrupoPrecificacao extends EntidadeDominio{

    private String nome;
    private Double margemLucro;

    public GrupoPrecificacao(Long id, String nome) {
        super(id);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }

}
