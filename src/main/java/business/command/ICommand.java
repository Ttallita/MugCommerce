package business.command;

import model.EntidadeDominio;
import model.Result;

import javax.servlet.http.HttpSession;

public interface ICommand {
    Result execute(EntidadeDominio entidade, String operacao);
}
