package model.carrinho;

import java.util.List;

import model.cliente.Cliente;

public class Carrinho {
    private Cliente cliente;    
    private List<ItemCarrinho> itensCarrinho;
    private Double frete;

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

    public Double calcularFrete(){
        return frete;
    }

    public void adicionarItens(){

    }

    public void removerItens(List<ItemCarrinho> itemCarrinhos){

    }

    public void realizarCompra(){

    }
}
