package model.produto;

import model.EntidadeDominio;

public class Categoria extends EntidadeDominio{

    private String nome;

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

}
