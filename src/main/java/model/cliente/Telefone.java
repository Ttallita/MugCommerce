package model.cliente;

import model.cliente.endereco.TelefoneType;

import java.util.Objects;

public class Telefone {
    private TelefoneType tipo;
    private String ddd;
    private String numero;

    public Telefone() {}

    public Telefone(TelefoneType tipo, String ddd, String numero) {
        this.tipo = tipo;
        this.ddd = ddd;
        this.numero = numero;
    }

    public TelefoneType getTipo() {
        return tipo;
    }

    public void setTipo(TelefoneType tipo) {
        this.tipo = tipo;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
