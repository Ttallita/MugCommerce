package model.cliente.endereco;

import com.google.gson.annotations.SerializedName;

public enum EnderecoType {

    @SerializedName("Cobrança")
    COBRANCA("Cobrança"),

    @SerializedName("Entrega")
    ENTREGA("Entrega"),

    @SerializedName("Cobrança e entrega")
    COBRANCA_ENTREGA("Cobrança e entrega"),
    ;

    public String nomeExibicao;
    EnderecoType(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

}
