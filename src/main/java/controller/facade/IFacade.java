package controller.facade;

import model.EntidadeDominio;
import model.Result;

import javax.servlet.http.HttpSession;

public interface IFacade {

    public abstract Result salvar(EntidadeDominio entidade);
    public abstract Result atualizar(EntidadeDominio entidade);
    public abstract Result deletar(EntidadeDominio entidade);
    public abstract Result listar(EntidadeDominio entidade, String operacao);

}
