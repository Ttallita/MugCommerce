package business.command;

import business.facade.Facade;
import business.facade.IFacade;

public abstract class AbstractCommand implements ICommand{
    protected IFacade facade = new Facade();
}
