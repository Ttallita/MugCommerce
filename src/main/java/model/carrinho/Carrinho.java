package model.carrinho;

import java.util.ArrayList;
import java.util.List;

import model.EntidadeDominio;
import model.cliente.Cliente;

public class Carrinho extends EntidadeDominio {
    private Cliente cliente;    
    private List<ItemCarrinho> itensCarrinho;

    public Carrinho() {
        super();
        itensCarrinho = new ArrayList<>();
    }

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

    public void addItem(ItemCarrinho item) {
        itensCarrinho.add(item);
    }
}
