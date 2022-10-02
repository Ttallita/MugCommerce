package model.venda;

import java.time.LocalDate;
import java.util.List;

import model.EntidadeDominio;
import model.cliente.CartaoDeCredito;
import model.cliente.endereco.Endereco;
import model.cupom.Cupom;
import model.cliente.Cliente;
import model.produto.Produto;

public class Venda extends EntidadeDominio {
    private Cliente cliente;
    private Endereco enderecoEntrega;
    private List<Produto> produtos;
    private Double precoTotal;
    private List<CartaoDeCredito> cartoesdeCreditos; //// TODO verificar se pode usar mais de um cartão de crédito
    private List<Cupom> cupons;
    private Double frete;
    private LocalDate dataCompra;
    private LocalDate dataEntrega;
    private boolean pagamentoAprovado;
    private VendaType vendaStatus;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public List<CartaoDeCredito> getCartoesdeCreditos() {
        return cartoesdeCreditos;
    }

    public void setCartoesdeCreditos(List<CartaoDeCredito> cartoesdeCreditos) {
        this.cartoesdeCreditos = cartoesdeCreditos;
    }

    public List<Cupom> getCupons() {
        return cupons;
    }

    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }

    public Double getFrete() {
        return frete;
    }

    public void setFrete(Double frete) {
        this.frete = frete;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public void setPagamentoAprovado(boolean pagamentoAprovado) {
        this.pagamentoAprovado = pagamentoAprovado;
    }

    public boolean getPagamentoAprovado() {
        return pagamentoAprovado;
    }

    public VendaType getVendaStatus() {
        return vendaStatus;
    }

    public void setVendaStatus(VendaType vendaStatus) {
        this.vendaStatus = vendaStatus;
    }
}
