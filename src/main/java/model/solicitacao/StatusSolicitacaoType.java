package model.solicitacao;

public enum StatusSolicitacaoType {

    SOLICITADA ("Solicitado(a)"),
    RECUSADA ("Recusado(a)"),
    ACEITA ("Aceito(a)"),
    ENVIADO ("Enviado(a)"), // enviado pelo cliente
    RECEBIDO ("Recebido(a)"), // recebido pelo ecommerce
    REALIZADA ("Realizado(a)"),
    ;

    private String nomeExibicao;

    StatusSolicitacaoType(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }
}
