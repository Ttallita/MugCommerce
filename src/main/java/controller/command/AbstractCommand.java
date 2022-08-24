package controller.command;

import controller.facade.Facade;
import controller.facade.IFacade;

public abstract class AbstractCommand implements ICommand{
    protected IFacade facade = new Facade();
}
