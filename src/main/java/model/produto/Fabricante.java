package model.produto;

import model.EntidadeDominio;

public class Fabricante extends EntidadeDominio {
    String nome;

    public Fabricante(Long id, String nome) {
        super(id);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
