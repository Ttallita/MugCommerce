package model.solicitacao;

import model.EntidadeDominio;
import model.cliente.Cliente;
import model.venda.Venda;

import java.time.LocalDate;

public class Cancelamento extends EntidadeDominio implements ISolicitacao {

    private Venda venda;
    private Cliente cliente;

    private LocalDate data;
    private StatusSolicitacaoType status;

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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public StatusSolicitacaoType getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacaoType status) {
        this.status = status;
    }
}
