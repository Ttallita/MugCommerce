package model.produto;

import model.EntidadeDominio;

public class Status extends EntidadeDominio {
    private StatusType tipoStatus;
    private String categoria;
    private String justificativa;

    public StatusType getTipo() {
        return tipoStatus;
    }

    public void setTipo(StatusType tipoStatus) {
        this.tipoStatus = tipoStatus;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getJustificativa() {
        return justificativa;
    }
    
    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
}
