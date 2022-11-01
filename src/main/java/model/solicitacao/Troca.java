package model.solicitacao;

import model.EntidadeDominio;
import model.cliente.Cliente;
import model.produto.Produto;
import model.venda.Venda;

import java.time.LocalDateTime;

public class Troca extends EntidadeDominio implements ISolicitacao {

    private Produto produto;
    private Venda venda;
    private Cliente cliente;

    private LocalDateTime data;
    private StatusSolicitacaoType status;

    private boolean isReentradaEstoque;

    public Troca() { }
    public Troca(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public Venda getVenda() {
        return venda;
    }

    @Override
    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public LocalDateTime getData() {
        return data;
    }

    @Override
    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public StatusSolicitacaoType getStatus() {
        return status;
    }

    @Override
    public void setStatus(StatusSolicitacaoType status) {
        this.status = status;
    }

    public boolean isReentradaEstoque() {
        return isReentradaEstoque;
    }

    public void setReentradaEstoque(boolean reentradaEstoque) {
        isReentradaEstoque = reentradaEstoque;
    }
}
