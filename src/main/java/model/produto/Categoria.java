package model.produto;

import model.EntidadeDominio;

public class Categoria extends EntidadeDominio{

    private String nome;

    public Categoria() {}

    public Categoria(Long id, String nome) {
        super(id);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
