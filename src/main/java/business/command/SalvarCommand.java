package business.command;

import model.EntidadeDominio;
import model.Result;

public class SalvarCommand extends AbstractCommand{

    @Override
    public Result execute(EntidadeDominio entidade, String operacao) {
        return facade.salvar(entidade);
    }
}
