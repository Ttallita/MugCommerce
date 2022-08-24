package model.cliente.endereco;

import java.util.List;

public class Pais {
    private Long id;
    private String nome;
    private String sigla;
    private double bacen;
    private List<Estado> estados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public double getBacen() {
        return bacen;
    }

    public void setBacen(double bacen) {
        this.bacen = bacen;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
}
