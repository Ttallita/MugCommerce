package business.viewHelper.impl;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.UsuarioType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author andre
 */
public class LoginViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario usuario = new Usuario();

        usuario.setEmail(email);
        usuario.setSenha(senha);

        return usuario;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<EntidadeDominio> entidades = result.getEntidades();

        if(entidades.isEmpty())
            result.setMsg("Usuario/Senha incorreto.");
        else {
            Usuario usuario = (Usuario) entidades.get(0);
            request.getSession().setAttribute("usuarioLogado", usuario);
        }

        if(result.getMsg() == null)
            response.sendRedirect("index.jsp");
        else {
            String[] msgErros = result.getMsg().split("\n");

            request.setAttribute("erro", true);
            request.setAttribute("mensagens", msgErros);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
