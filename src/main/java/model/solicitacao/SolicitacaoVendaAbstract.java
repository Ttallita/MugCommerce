package model.solicitacao;

import model.venda.Venda;

/**
 * Solicitações são geradas a partir de vendas, elas são criadas APENAS pelo cliente
 */
public class SolicitacaoVendaAbstract {

    private SolicitacaoStatusType statusSolicitacao;
    private Venda venda;

    public SolicitacaoStatusType getStatusSolicitacao() {
        return statusSolicitacao;
    }

    public void setStatusSolicitacao(SolicitacaoStatusType statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

}
