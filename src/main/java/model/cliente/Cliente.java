package model.cliente;

import model.Usuario;
import model.cliente.endereco.Endereco;
import model.cupom.Cupom;

import java.time.LocalDate;
import java.util.List;

public class Cliente extends Usuario {

    private String nome;
    private String sobrenome;
    private String genero;
    private LocalDate dataNascimento;
    private String cpf;
    private Telefone telefone;
    private List<Endereco> enderecos;
    private List<CartaoDeCredito> cartoesDeCredito;
    private List<Cupom> cupons;
    private int ranking;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<CartaoDeCredito> getCartoesDeCredito() {
        return cartoesDeCredito;
    }

    public void setCartoesDeCredito(List<CartaoDeCredito> cartoesDeCredito) {
        this.cartoesDeCredito = cartoesDeCredito;
    }

    public List<Cupom> getCupons() {
        return cupons;
    }

    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
