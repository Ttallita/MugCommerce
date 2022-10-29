package model.venda;

import model.EntidadeDominio;
import model.carrinho.Carrinho;
import model.cliente.CartaoDeCredito;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import model.cupom.Cupom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Venda extends EntidadeDominio {
    private Cliente cliente;
    private Endereco enderecoEntrega;
    private Endereco enderecoCobranca;
    private Carrinho carrinho;
    private List<CartaoDeCredito> cartoes = new ArrayList<>();
    private List<Cupom> cupons = new ArrayList<>();

    private Double valorItens;
    private Double frete;
    private Double precoTotal;

    private LocalDate dataCompra;
    private LocalDate dataEnvio;
    private LocalDate dataEntrega;

    private boolean pagamentoAprovado;
    private StatusVendaType vendaStatus;

    public Venda(){}

    public Venda(Cliente cliente){
        this.cliente = cliente;
    }

    public double calculaFrete(){
        int quantTotalItens = carrinho != null ? carrinho.getQuantTotalItens() : 1;

        return Math.max(1, quantTotalItens / 10) * 10.0;
    }

    public double getCalculaTotalEntrega(){
        double valorDesconto = 0;

        if(cupons != null)
            valorDesconto = cupons.stream()
                    .map(Cupom::getValor)
                    .mapToDouble(Double::doubleValue)
                    .sum();


        return  carrinho.getTotalCarrinho() + calculaFrete() - valorDesconto;
    }

    public LocalDate calculaDataEntrega(){
        return LocalDate.now().plusDays(15L);
    }

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

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public List<CartaoDeCredito> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<CartaoDeCredito> cartoes) {
        this.cartoes = cartoes;
    }

    public void addCartaoDeCredito(CartaoDeCredito cartaoDeCredito){
        this.cartoes.add(cartaoDeCredito);
    }

    public List<Cupom> getCupons() {
        return cupons;
    }

    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }

    public void addCupom(Cupom cupom){
        if(cupons != null)
            this.cupons.add(cupom);
        else {
            this.cupons = new ArrayList<>();
            this.cupons.add(cupom);
        }
    }

    public Double getValorItens() {
        return valorItens;
    }

    public void setValorItens(Double valorItens) {
        this.valorItens = valorItens;
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

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
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

    public StatusVendaType getVendaStatus() {
        return vendaStatus;
    }

    public void setVendaStatus(StatusVendaType vendaStatus) {
        this.vendaStatus = vendaStatus;
    }

    public Endereco getEnderecoCobranca() {
        return enderecoCobranca;
    }

    public void setEnderecoCobranca(Endereco enderecoCobranca) {
        this.enderecoCobranca = enderecoCobranca;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "cliente=" + cliente +
                ", enderecoEntrega=" + enderecoEntrega +
                ", carrinho=" + carrinho +
                ", cartoes=" + cartoes +
                ", cupons=" + cupons +
                ", valorItens=" + valorItens +
                ", frete=" + frete +
                ", precoTotal=" + precoTotal +
                ", dataCompra=" + dataCompra +
                ", dataEntrega=" + dataEntrega +
                ", pagamentoAprovado=" + pagamentoAprovado +
                ", vendaStatus=" + vendaStatus +
                '}';
    }
}
