package filter;

import model.Usuario;
import model.UsuarioType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/login.jsp", "/cadastro.jsp"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        Usuario usuarioLogado = (Usuario) req.getSession().getAttribute("usuarioLogado");

        if(usuarioLogado != null)
            if (usuarioLogado.getTipoUsuario().equals(UsuarioType.ADMINISTRADOR))
                res.sendRedirect("/emug/adm/clientes?operacao=listarTodos");
            else
                res.sendRedirect("/emug/index.jsp");

        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() {}
}
