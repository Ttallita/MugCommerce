package business.viewHelper.impl.model;

import business.viewHelper.IViewHelper;
import dao.AuditoriaDAO;
import dao.UsuarioDAO;
import model.AuditoriaType;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsuarioViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        if(operacao.equals("atualizar")) {
            Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");


            Usuario usuario = new Usuario();
            usuario.setId(usuarioLogado.getId());
            usuario.setEmail(usuarioLogado.getEmail());
            usuario.setSenha(request.getParameter("senhaNova"));
            usuario.setConfirmarSenha(request.getParameter("senhaConfirmacao"));
            usuario.setTipoUsuario(usuarioLogado.getTipoUsuario());
            usuario.setAtivo(usuarioLogado.isAtivo());
            usuario.setSenhaAntiga(request.getParameter("senhaAntiga"));

            return usuario;
        } else if (operacao.equals("excluir"))
            return (Usuario) request.getSession().getAttribute("usuarioLogado");


        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        if(operacao.equals("atualizar")) {
            List<EntidadeDominio> entidades = result.getEntidades();

            if(entidades.isEmpty())
                result.setMsg("Usuario/Senha incorreto.");

            String[] mensagens;

            if(result.getMsg() == null) {
                mensagens = new String[] { "Senha Atualizada com sucesso." };
                request.getSession().setAttribute("usuarioLogado", entidades.get(0));
                new AuditoriaDAO().salvar(Utils.criaAuditoria(entidades.get(0), AuditoriaType.ALTERACAO, (Usuario) entidades.get(0)));
            } else
                mensagens = result.getMsg().split("\n");

            request.setAttribute("erro", result.getMsg() != null);
            request.setAttribute("mensagens", mensagens);
            request.getRequestDispatcher("/cliente/atualizarSenha.jsp").forward(request, response);
        } else {
            request.getSession().removeAttribute("usuarioLogado");
            response.sendRedirect("/emug/index.jsp");
        }

    }

}
