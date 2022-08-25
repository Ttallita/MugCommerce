package controller.command;

import model.EntidadeDominio;
import model.Result;

public class AtualizarCommand extends AbstractCommand{

    @Override
    public Result execute(EntidadeDominio entidade, String operacao) {
        return facade.atualizar(entidade);
    }
}
