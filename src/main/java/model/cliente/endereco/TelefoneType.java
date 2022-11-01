package model.cliente.endereco;

import com.google.gson.annotations.SerializedName;

public enum TelefoneType {
    @SerializedName("Celular")
    CELULAR("Celular"),

    @SerializedName("Residencial")
    RESIDENCIAL("Residencial"),
    ;

    public String nomeExibicao;

    TelefoneType(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

}
