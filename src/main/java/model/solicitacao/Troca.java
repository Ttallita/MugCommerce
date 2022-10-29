package model.solicitacao;

import model.EntidadeDominio;
import model.cliente.Cliente;
import model.produto.Produto;
import model.venda.Venda;

import java.time.LocalDate;
import java.util.Date;

public class Troca extends EntidadeDominio implements ISolicitacao {

    private Produto produto;
    private Venda venda;
    private Cliente cliente;

    private LocalDate data;
    private StatusSolicitacaoType status;

    public Troca(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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
