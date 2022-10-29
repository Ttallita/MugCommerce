package model.cliente.endereco;

import model.EntidadeDominio;
import model.cliente.Cliente;

import java.util.Objects;

public class Endereco extends EntidadeDominio {

    private String tipoResidencia;
    private String tipoLogradouro;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String observacoes;
    private EnderecoType tipoEndereco;
    private String apelido;
    private transient Cliente cliente;

    public Endereco() {}

    public Endereco(Cliente cliente){
        this.cliente = cliente;
    }

    public String getTipoResidencia() {
        return tipoResidencia;
    }

    public void setTipoResidencia(String tipoResidencia) {
        this.tipoResidencia = tipoResidencia;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public EnderecoType getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(EnderecoType tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    protected boolean canEqual(Object obj) {
        return (obj instanceof Endereco);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco endereco)) return false;
        return Objects.equals(tipoResidencia, endereco.tipoResidencia)
                && Objects.equals(tipoLogradouro, endereco.tipoLogradouro)
                && Objects.equals(logradouro, endereco.logradouro)
                && Objects.equals(numero, endereco.numero)
                && Objects.equals(bairro, endereco.bairro)
                && Objects.equals(cep, endereco.cep)
                && Objects.equals(cidade, endereco.cidade)
                && Objects.equals(estado, endereco.estado)
                && Objects.equals(observacoes, endereco.observacoes)
                && tipoEndereco == endereco.tipoEndereco
                && Objects.equals(apelido, endereco.apelido)
                && endereco.canEqual(this);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(tipoResidencia, tipoLogradouro, logradouro, numero, bairro, cep, cidade, estado, observacoes, tipoEndereco, apelido);
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "tipoResidencia='" + tipoResidencia + '\'' +
                ", tipoLogradouro='" + tipoLogradouro + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", observacoes='" + observacoes + '\'' +
                ", tipoEndereco=" + tipoEndereco +
                ", apelido='" + apelido + '\'' +
                '}';
    }
}
