package model.cliente;

import dao.cliente.ClienteDAO;
import model.EntidadeDominio;

import java.util.Objects;

public class CartaoDeCredito extends EntidadeDominio {
    private String numCartao;

    private String finalCartao;
    private String nomeImpressoCartao;
    private String bandeira;
    private String mesValidade;
    private String anoValidade;
    private Integer codigo;
    private boolean preferencial;
    private transient Cliente cliente;

    public CartaoDeCredito(){}

    public CartaoDeCredito(Cliente cliente){
        this.cliente = cliente;
    }

    public String getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;

        if(numCartao != null && !numCartao.isBlank())
            setFinalCartao(numCartao.split(" ")[3]);
    }

    public String getFinalCartao() {
        return finalCartao;
    }

    private void setFinalCartao(String finalCartao) {
        this.finalCartao = finalCartao;
    }

    public String getNomeImpressoCartao() {
        return nomeImpressoCartao;
    }

    public void setNomeImpressoCartao(String nomeImpressoCartao) {
        this.nomeImpressoCartao = nomeImpressoCartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getMesValidade() {
        return mesValidade;
    }

    public void setMesValidade(String mesValidade) {
        this.mesValidade = mesValidade;
    }

    public String getAnoValidade() {
        return anoValidade;
    }
    
    public void setAnoValidade(String anoValidade) {
        this.anoValidade = anoValidade;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public boolean isPreferencial() {
        return preferencial;
    }

    public void setPreferencial(boolean preferencial) {
        this.preferencial = preferencial;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    protected boolean canEqual(Object obj) {
        return (obj instanceof CartaoDeCredito);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartaoDeCredito that)) return false;
        return preferencial == that.preferencial
                && Objects.equals(numCartao, that.numCartao)
                && Objects.equals(nomeImpressoCartao, that.nomeImpressoCartao)
                && Objects.equals(bandeira, that.bandeira)
                && Objects.equals(mesValidade, that.mesValidade)
                && Objects.equals(anoValidade, that.anoValidade)
                && Objects.equals(codigo, that.codigo)
                && that.canEqual(this);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(numCartao, nomeImpressoCartao, bandeira, mesValidade, anoValidade, codigo, preferencial);
    }

    @Override
    public String toString() {
        return "CartaoDeCredito{" +
                "numCartao='" + numCartao + '\'' +
                ", finalCartao='" + finalCartao + '\'' +
                ", nomeImpressoCartao='" + nomeImpressoCartao + '\'' +
                ", bandeira='" + bandeira + '\'' +
                ", mesValidade=" + mesValidade +
                ", anoValidade=" + anoValidade +
                ", codigo=" + codigo +
                ", preferencial=" + preferencial +
                ", cliente=" + cliente +
                '}';
    }
}
