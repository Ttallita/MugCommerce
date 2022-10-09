package model;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private String msg;
    private List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<EntidadeDominio> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<EntidadeDominio> entidades) {
        this.entidades = entidades;
    }
}
