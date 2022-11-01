package business.viewHelper;

import model.EntidadeDominio;
import model.Result;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IViewHelper {

    EntidadeDominio getEntidade(HttpServletRequest request);

    void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    default Usuario getUsuarioLogado(HttpServletRequest request){
        return (Usuario) request.getSession().getAttribute("usuarioLogado");
    }
}
