package model;

public abstract class EntidadeDominio {
    private Long id;
    private boolean ativo;

    public EntidadeDominio() {}

    public EntidadeDominio(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
