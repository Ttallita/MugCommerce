package controller.command;

import model.EntidadeDominio;
import model.Result;

public class ListarCommand extends AbstractCommand{
    @Override
    public Result execute(EntidadeDominio entidade, String operacao) {
        return facade.listar(entidade, operacao);
    }
}
