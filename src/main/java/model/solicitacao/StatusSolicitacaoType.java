package model.solicitacao;

import java.util.List;

public enum StatusSolicitacaoType {

    SOLICITADA ("Solicitado(a)"),
    RECUSADA ("Recusado(a)"),
    ACEITA ("Aceito(a)"),
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

    public static List<StatusSolicitacaoType> getProximoStatus(StatusSolicitacaoType status) {
        if(status.equals(SOLICITADA))
            return List.of(RECUSADA, ACEITA);
        else if (status.equals(ACEITA))
            return List.of(REALIZADA);
        else
            return List.of();
    }
}
