package model.venda;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import model.EntidadeDominio;
import model.carrinho.Carrinho;
import model.cliente.CartaoDeCredito;
import model.cliente.endereco.Endereco;
import model.cupom.Cupom;
import model.cliente.Cliente;

public class Venda extends EntidadeDominio {
    private Cliente cliente;
    private Endereco enderecoEntrega;
    private Carrinho carrinho;
    private CartaoDeCredito cartao; //// TODO verificar se pode usar mais de um cartão de crédito
    private List<Cupom> cupons;

    private Double valorItens;
    private Double frete;
    private Double precoTotal;

    private LocalDate dataCompra;
    private LocalDate dataEntrega;

    private boolean pagamentoAprovado;
    private VendaType vendaStatus;

    public void atualizarFrete(){
        int quantTotalItens = carrinho != null ? carrinho.getQuantTotalItens() : 1;
        int distanciaEndereco = new Random(10L).nextInt(30);

        frete = Math.max(1, quantTotalItens / 10) * 10.0 * distanciaEndereco;
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
        this.atualizarFrete();
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
        this.atualizarFrete();
    }

    public CartaoDeCredito getCartao() {
        return cartao;
    }

    public void setCartao(CartaoDeCredito cartao) {
        this.cartao = cartao;
    }

    public List<Cupom> getCupons() {
        return cupons;
    }

    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
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

    @Override
    public String toString() {
        return "Venda{" +
                "cliente=" + cliente +
                ", enderecoEntrega=" + enderecoEntrega +
                ", carrinho=" + carrinho +
                ", cartao=" + cartao +
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
