package model.venda;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public enum StatusVendaType {

    @SerializedName("Em processamento")
    EM_PROCESSAMENTO("Em processamento"),

    @SerializedName("Pagamento realizado")
    PAGAMENTO_REALIZADO ("Pagamento realizado"),

    @SerializedName("Em transporte")
    EM_TRANSPORTE ("Em transporte"),

    @SerializedName("Entrega realizada")
    ENTREGA_REALIZADA("Entrega realizada"),

    @SerializedName("Pedido de troca")
    PEDIDO_TROCA("Pedido de troca"),

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

    public static List<StatusVendaType> getProximoStatus(StatusVendaType status) {
        if(status.equals(EM_PROCESSAMENTO))
            return List.of(PAGAMENTO_REALIZADO);
        else if(status.equals(PAGAMENTO_REALIZADO))
            return List.of(EM_TRANSPORTE);
        else if(status.equals(EM_TRANSPORTE))
            return List.of(ENTREGA_REALIZADA);
        else if(status.equals(ENTREGA_REALIZADA))
            return List.of(FINALIZADO, CANCELADA);
        else
            return List.of();
    }

}
