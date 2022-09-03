package business.facade;

import model.EntidadeDominio;
import model.Result;

import javax.servlet.http.HttpSession;

public interface IFacade {

    Result salvar(EntidadeDominio entidade);
    Result atualizar(EntidadeDominio entidade);
    Result deletar(EntidadeDominio entidade);
    Result listar(EntidadeDominio entidade, String operacao);

}
