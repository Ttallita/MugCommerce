package session.carrinho;

import model.EntidadeDominio;

import javax.servlet.http.HttpSession;

public class CarrinhoSessionUtil implements session.ISessionUtil {

    @Override
    public void salvar(EntidadeDominio entidade, HttpSession session) {
        session.setAttribute("carrinho", entidade);
    }

    @Override
    public void atualizar(EntidadeDominio entidade, HttpSession session) {

    }

    @Override
    public void remover(EntidadeDominio entidade, HttpSession session) {

    }

    @Override
    public void listar(EntidadeDominio entidade, HttpSession session, String operacao) {

    }
}
