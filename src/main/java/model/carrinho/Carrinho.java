package model.carrinho;

import model.EntidadeDominio;
import model.cliente.Cliente;

import java.util.ArrayList;
import java.util.List;

public class Carrinho extends EntidadeDominio {
    private Cliente cliente;    
    private List<ItemCarrinho> itensCarrinho;

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

    public List<ItemCarrinho> getItensCarrinho() {
        return itensCarrinho;
    }

    public void setItensCarrinho(List<ItemCarrinho> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }

    public void addItem(ItemCarrinho item) {
        itensCarrinho.add(item);
    }

    public void removeItem(ItemCarrinho item) {
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

    public int getQuantTotalItens(){
        return itensCarrinho.stream()
                .map(ItemCarrinho::getQuant)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
