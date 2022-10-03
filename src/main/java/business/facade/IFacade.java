package business.facade;

import model.EntidadeDominio;
import model.Result;

import javax.servlet.http.HttpSession;

public interface IFacade {

    Result salvar(EntidadeDominio entidade, HttpSession session);
    Result atualizar(EntidadeDominio entidade, HttpSession session);
    Result deletar(EntidadeDominio entidade, HttpSession session);
    Result listar(EntidadeDominio entidade,  HttpSession session, String operacao);

}
