package business.command;

import business.facade.Facade;
import business.facade.IFacade;
import model.EntidadeDominio;
import model.Result;

import javax.servlet.http.HttpSession;

public abstract class AbstractCommand{
    protected IFacade facade = new Facade();
    public abstract Result execute(EntidadeDominio entidade, HttpSession session, String operacao);
}
