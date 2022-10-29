package business.viewHelper.impl.model.solicitacao;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.cliente.Cliente;
import model.solicitacao.Troca;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TrocaViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        switch (operacao) {
            case "listar" -> {
                Troca troca = new Troca(new Cliente(usuarioLogado));
                return troca;
            }
        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        switch (operacao) {
            case "listar" -> {
                request.setAttribute("solicitacoes", result.getEntidades());
                request.getRequestDispatcher("/cliente/solicitacoes.jsp").forward(request, response);
            }
        }
    }

}
