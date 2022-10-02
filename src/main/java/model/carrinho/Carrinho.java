package model.carrinho;

import java.util.List;

import model.EntidadeDominio;
import model.cliente.Cliente;

public class Carrinho extends EntidadeDominio {
    private Cliente cliente;    
    private List<ItemCarrinho> itensCarrinho;
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemCarrinho> getItensCarrinho() {
        return itensCarrinho;
    }
    public void setItensCarrinho(List<ItemCarrinho> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }
}
