package business.viewHelper.impl.model.venda;

import business.viewHelper.IViewHelper;
import dao.cliente.CupomDAO;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.cliente.Cliente;
import model.cupom.Cupom;
import utils.UtilsWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CupomViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        Cliente cliente = new Cliente(usuarioLogado);

        Cupom cupom = new Cupom();
        cupom.setCliente(cliente);

        if ("listarUnico".equals(operacao)){
            String idCupom = request.getParameter("id");
            cupom.setId(idCupom != null && !idCupom.isEmpty() ? Long.parseLong(idCupom) : null);

            return cupom;
        }

        if ("listar".equals(operacao)){
            return cupom;
        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UtilsWeb.montaRespostaJson(result, request, response);
    }

}
