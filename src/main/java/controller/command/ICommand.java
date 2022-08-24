package controller.command;

import model.EntidadeDominio;
import model.Result;

import javax.servlet.http.HttpSession;

public interface ICommand {

    public Result execute(EntidadeDominio entidade, String operacao);
}
