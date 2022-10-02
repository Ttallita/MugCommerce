package business.viewHelper.impl.model.venda;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.venda.Venda;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VendaViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        Venda venda = new Venda();

//        request.getParameter("carrinho");
//        venda.setProdutos(produtos);

        return venda;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        if("listar".equals(operacao)) {
//          request.setAttribute("clientes", result.getEntidades());
            request.getRequestDispatcher("/cliente/finalizarCompra.jsp").forward(request, response);
        }
    }

}
