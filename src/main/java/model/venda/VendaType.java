package model.venda;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;

public enum VendaType {

    @SerializedName("Em processamento")
    EM_PROCESSAMENTO ("Em processamento"),

    @SerializedName("Enviado para transportadora")
    PAGAMENTO_REALIZADO ("Enviado para transportadora"),

    @SerializedName("Enviado para transportadora")
    ENVIADO_PARA_TRANSPORTADORA ("Enviado para transportadora"),

    @SerializedName("Em transporte")
    EM_TRANSPORTE ("Em transporte"),

    @SerializedName("Entregue")
    ENTREGUE ("Entregue"),
    ;

    public String nomeExibicao;

    VendaType(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    @JsonValue
    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

}
