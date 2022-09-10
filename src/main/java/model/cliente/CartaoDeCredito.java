package model.cliente;

import model.EntidadeDominio;

import java.util.Objects;

public class CartaoDeCredito extends EntidadeDominio {
    private String numCartao;
    private String nomeImpressoCartao;
    private String bandeira;
    private int mesValidade;
    private int anoValidade;
    private Integer codigo;
    private boolean preferencial;
    private Cliente cliente;

    public String getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
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

    public int getMesValidade() {
        return mesValidade;
    }

    public void setMesValidade(int mesValidade) {
        this.mesValidade = mesValidade;
    }

    public int getAnoValidade() {
        return anoValidade;
    }
    
    public void setAnoValidade(int anoValidade) {
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
}
