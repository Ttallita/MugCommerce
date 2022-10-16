package model.estoque;

import model.EntidadeDominio;

public class Fornecedor extends EntidadeDominio {
    private String nome;


    public Fornecedor(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
