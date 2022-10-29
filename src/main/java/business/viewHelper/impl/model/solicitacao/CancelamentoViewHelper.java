package business.viewHelper.impl.model.solicitacao;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.cliente.Cliente;
import model.solicitacao.Cancelamento;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelamentoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        switch (operacao) {
            case "listar" -> {
                Cancelamento cancelamento = new Cancelamento(new Cliente(usuarioLogado));
                return cancelamento;
            }
        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        switch (operacao) {
            case "listar" -> {
                request.setAttribute("solicitacao", result.getEntidades());
                request.getRequestDispatcher("/cliente/solicitacoes.jsp").forward(request, response);
            }
        }
    }

}
