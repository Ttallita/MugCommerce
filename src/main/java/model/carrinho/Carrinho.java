package model.carrinho;

import java.util.ArrayList;
import java.util.List;

import model.EntidadeDominio;
import model.cliente.Cliente;

public class Carrinho extends EntidadeDominio {
    private Cliente cliente;    
    private List<ItemCompra> itensCarrinho;

    private double totalCarrinho;

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

    public List<ItemCompra> getItensCarrinho() {
        return itensCarrinho;
    }

    public void setItensCarrinho(List<ItemCompra> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }

    public void addItem(ItemCompra item) {
        itensCarrinho.add(item);
    }

    public void removeItem(ItemCompra item) {
        boolean removeu = itensCarrinho.removeIf(itemCarrinho -> item.getProduto().getId().equals(itemCarrinho.getProduto().getId()));

        if(!removeu)
            throw new RuntimeException("Item de id " + item.getId() + " não foi removido");
    }

    public double getTotalCarrinho() {
        return itensCarrinho.stream()
                .map(item -> item.getProduto().getValorVenda() * item.getQuant())
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
