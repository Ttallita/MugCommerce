package business.strategy;

import model.EntidadeDominio;

public interface IStrategy {
    public String processa(EntidadeDominio entidade);
}
