package model.solicitacao;

import model.venda.Venda;

/**
 * Solicitações são geradas a partir de vendas, elas são criadas APENAS pelo cliente
 */
public class SolicitacaoVendaAbstract {

    private StatusSolicitacaoType statusSolicitacao;
    private Venda venda;

    public StatusSolicitacaoType getStatusSolicitacao() {
        return statusSolicitacao;
    }

    public void setStatusSolicitacao(StatusSolicitacaoType statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

}
