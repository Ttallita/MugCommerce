package model.cliente;

import model.EntidadeDominio;
import model.Usuario;
import model.cliente.endereco.Endereco;
import model.cupom.Cupom;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Cliente extends EntidadeDominio {

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
    private Usuario usuario;

    public Cliente() {}

    public Cliente(Usuario usuario) {
        super();
        this.usuario = usuario;
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    protected boolean canEqual(Object obj) {
        return (obj instanceof Cliente);
    }
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return ranking == cliente.ranking
                && Objects.equals(nome, cliente.nome)
                && Objects.equals(sobrenome, cliente.sobrenome)
                && Objects.equals(genero, cliente.genero)
                && Objects.equals(dataNascimento, cliente.dataNascimento)
                && Objects.equals(cpf, cliente.cpf)
                && Objects.equals(telefone, cliente.telefone)
                && Objects.equals(enderecos, cliente.enderecos)
                && Objects.equals(cartoesDeCredito, cliente.cartoesDeCredito)
                && Objects.equals(cupons, cliente.cupons)
                && Objects.equals(usuario, cliente.usuario)
                && cliente.canEqual(this);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(nome, sobrenome, genero, dataNascimento, cpf, telefone, enderecos, cartoesDeCredito, cupons, ranking, usuario);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", genero='" + genero + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cpf='" + cpf + '\'' +
                ", telefone=" + telefone +
                ", enderecos=" + enderecos +
                ", cartoesDeCredito=" + cartoesDeCredito +
                ", cupons=" + cupons +
                ", ranking=" + ranking +
                ", usuario=" + usuario +
                '}';
    }
}
