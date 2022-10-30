package model.solicitacao;

import model.cliente.Cliente;
import model.venda.Venda;

import java.time.LocalDate;

/**
 * Solicitações são geradas a partir de vendas, elas são criadas APENAS pelo cliente
 */
public interface ISolicitacao {

    Venda getVenda();

    void setVenda(Venda venda);

    Cliente getCliente();

    void setCliente(Cliente cliente);

    LocalDate getData();

    void setData(LocalDate data);

    StatusSolicitacaoType getStatus();

    void setStatus(StatusSolicitacaoType status);
}
