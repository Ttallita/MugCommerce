package model.cupom;

import model.EntidadeDominio;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import model.venda.Venda;

import java.time.LocalDate;
import java.util.Objects;

public class Cupom extends EntidadeDominio {

    private transient Cliente cliente;
    private String nome;
    private Venda venda;
    private CupomType tipo;
    private Double valor;
    private LocalDate dataValidade;
    private String descricao;

    public Cupom(){}

    public Cupom(Cliente cliente){
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public CupomType getTipo() {
        return tipo;
    }

    public void setTipo(CupomType tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    protected boolean canEqual(Object obj) {
        return (obj instanceof Endereco);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cupom)) return false;
        Cupom cupom = (Cupom) o;
        return  Objects.equals(nome, cupom.nome)
                && Objects.equals(venda, cupom.venda)
                && tipo == cupom.tipo
                && Objects.equals(valor, cupom.valor)
                && Objects.equals(dataValidade, cupom.dataValidade)
                && Objects.equals(descricao, cupom.descricao)
                && cupom.canEqual(this);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(nome, venda, tipo, valor, dataValidade, descricao);
    }

    @Override
    public String toString() {
        return "Cupom{" +
                " nome='" + nome + '\'' +
                ", venda=" + venda +
                ", tipo=" + tipo +
                ", valor=" + valor +
                ", dataValidade=" + dataValidade +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
