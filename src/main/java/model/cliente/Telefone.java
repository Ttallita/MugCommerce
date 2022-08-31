package model.cliente;

import model.cliente.endereco.TelefoneType;

import java.util.Objects;

public class Telefone {
    private TelefoneType tipo;
    private String ddd;
    private String numero;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return Objects.equals(tipo, telefone.tipo)
                && Objects.equals(ddd, telefone.ddd)
                && Objects.equals(numero, telefone.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, ddd, numero);
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "tipo='" + tipo + '\'' +
                ", ddd='" + ddd + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
