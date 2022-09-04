package business.viewHelper.impl.model.adm;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.cliente.Cliente;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClienteAdmViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        if(operacao.equals("listarTodos"))
            return new Cliente();

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        if ("listarTodos".equals(operacao)) {
            request.setAttribute("clientes", result.getEntidades());
            request.getRequestDispatcher("/gerenciar/clientes.jsp").forward(request, response);
        }

    }
}
