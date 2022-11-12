package business.viewHelper.impl.model.produto;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.produto.GrupoPrecificacao;
import utils.UtilsWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GrupoPrecificacaoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        if(operacao.equals("listar")) {
            return new GrupoPrecificacao();
        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UtilsWeb.montaRespostaJson(result, response);
    }
}
