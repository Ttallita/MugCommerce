package model.venda;

public enum SolicitacaoType {

    TROCA_SOLICITADA ("Troca solicitada"),
    CANCELAMENTO_SOLICITADO ("CANCELAMENTO_SOLICITADO"),

    TROCA_RECUSADA ("TROCA_RECUSADA"),
    CANCELAMENTO_RECUSADO ("CANCELAMENTO_RECUSADO"),

    TROCA_ACEITA ("TROCA_ACEITA"),
    CANCELAMENTO_ACEITO ("CANCELAMENTO_ACEITO"),

    PRODUTO_ENVIADO_PARA_TROCA ("PRODUTO_ENVIADO_PARA_TROCA"),
    PRODUTO_ENVIADO_PARA_CANCELAMENTO ("PRODUTO_ENVIADO_PARA_CANCELAMENTO"),

    PRODUTO_RECEBIDO_PARA_TROCA ("PRODUTO_RECEBIDO_PARA_TROCA"),
    PRODUTO_RECEBIDO_PARA_CANCELAMENTO ("PRODUTO_RECEBIDO_PARA_CANCELAMENTO"),

    TROCA_REALIZADA ("TROCA_REALIZADA"),
    CANCELAMENTO_REALIZADO ("CANCELAMENTO_REALIZADO"),
    ;

    private String nomeExibicao;

    SolicitacaoType(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }
}
