package business.viewHelper;

import model.EntidadeDominio;
import model.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IViewHelper {

    EntidadeDominio getEntidade(HttpServletRequest request);

    void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
