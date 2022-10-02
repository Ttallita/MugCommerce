package business.command;

import model.EntidadeDominio;
import model.Result;

import javax.servlet.http.HttpSession;

public class ExcluirCommand extends AbstractCommand{

    @Override
    public Result execute(EntidadeDominio entidade, HttpSession session, String operacao) {
        return facade.deletar(entidade, session);
    }
}
