package session;

import model.EntidadeDominio;

import javax.servlet.http.HttpSession;

public interface ISessionUtil {
    void salvar(EntidadeDominio entidade, HttpSession session);
    void atualizar(EntidadeDominio entidade, HttpSession session);
    void remover(EntidadeDominio entidade, HttpSession session);
    void listar(EntidadeDominio entidade, HttpSession session, String operacao);
}
