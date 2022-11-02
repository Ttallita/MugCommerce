package model.venda;

import com.google.gson.annotations.SerializedName;

public enum StatusVendaType {

    @SerializedName("Em análise")
    EM_PROCESSAMENTO("Em processamento"),

    @SerializedName("Pagamento realizado")
    PAGAMENTO_REALIZADO ("Pagamento realizado"),

    @SerializedName("Em transporte")
    EM_TRANSPORTE ("Em transporte"),

    @SerializedName("Entrega realizada")
    ENTREGA_REALIZADA("Entrega realizada"),

    @SerializedName("Finalizado")
    FINALIZADO("Finalizado"),

    @SerializedName("Cancelada")
    CANCELADA("Cancelada"),
    ;

    public String nomeExibicao;

    StatusVendaType(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

}
