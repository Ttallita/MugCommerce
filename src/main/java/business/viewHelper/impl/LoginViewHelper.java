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

        String operacao = request.getParameter("operacao");

        Usuario usuario = new Usuario();

        if(operacao.equals("login")) {
            usuario.setEmail(email);
            usuario.setSenha(senha);
        }

        return usuario;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        if(operacao.equals("login")) {
            if(result.getMsg() == null) {
                List<EntidadeDominio> entidades = result.getEntidades();

                if(entidades.size() > 0) {
                    Usuario usuario = (Usuario) entidades.get(0);

                    request.getSession().setAttribute("usuarioLogado", usuario);

                    if(usuario.getTipoUsuario().equals(UsuarioType.CLIENTE)) {
                        response.sendRedirect("index.jsp");
                    } else {
                        response.sendRedirect("/LootCommerce/admin/controle?operacao=listarTodos");
                    }

                } else {
                    request.setAttribute("mensagem", "Email e/ou senha incorretos");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                String[] msgErros = result.getMsg().split("\n");
                request.setAttribute("mensagem", msgErros);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }
}
