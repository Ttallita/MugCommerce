package model;

import java.time.LocalDateTime;

public class Auditoria extends EntidadeDominio{
    private LocalDateTime data;
    private Usuario usuario;
    private AuditoriaType tipo;
    private String json;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public AuditoriaType getTipo() {
        return tipo;
    }

    public void setTipo(AuditoriaType tipo) {
        this.tipo = tipo;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
