package filter;

import model.Usuario;
import model.UsuarioType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({ "/cliente/*", "/clientes/*" })
public class PerfilFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if(usuario != null) {
            if(usuario.getTipoUsuario().equals(UsuarioType.ADMINISTRADOR))
                response.sendRedirect("/emug/adm/clientes?operacao=listarTodos");
        } else
            response.sendRedirect("/emug/index.jsp");

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
