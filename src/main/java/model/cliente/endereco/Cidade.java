package model.cliente.endereco;

public class Cidade {
    private Long id;
    private String nome;
    private double ibge;

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

    public double getIbge() {
        return ibge;
    }

    public void setIbge(double ibge) {
        this.ibge = ibge;
    }
}
