package model.solicitacao;

import model.EntidadeDominio;
import model.cliente.Cliente;
import model.venda.Venda;

import java.time.LocalDateTime;

public class Cancelamento extends EntidadeDominio implements ISolicitacao {

    private Venda venda;
    private Cliente cliente;

    private LocalDateTime data;
    private StatusSolicitacaoType status;

    private boolean reentradaEstoque;

    public Cancelamento(){ }

    public Cancelamento(Cliente cliente){
        this.cliente = cliente;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public StatusSolicitacaoType getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacaoType status) {
        this.status = status;
    }

    public boolean isReentradaEstoque() {
        return reentradaEstoque;
    }

    public void setReentradaEstoque(boolean reentradaEstoque) {
        this.reentradaEstoque = reentradaEstoque;
    }
}
