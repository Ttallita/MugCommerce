package model.solicitacao;

import model.cliente.Cliente;
import model.venda.Venda;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Solicitações são geradas a partir de vendas, elas são criadas APENAS pelo cliente
 */
public interface ISolicitacao {

    Venda getVenda();

    void setVenda(Venda venda);

    Cliente getCliente();

    void setCliente(Cliente cliente);

    LocalDateTime getData();

    void setData(LocalDateTime data);

    StatusSolicitacaoType getStatus();

    void setStatus(StatusSolicitacaoType status);
}
