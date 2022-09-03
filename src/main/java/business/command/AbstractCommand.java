package business.command;

import business.facade.Facade;
import business.facade.IFacade;
import model.EntidadeDominio;
import model.Result;

public abstract class AbstractCommand{
    protected IFacade facade = new Facade();
    public abstract Result execute(EntidadeDominio entidade, String operacao);
}
