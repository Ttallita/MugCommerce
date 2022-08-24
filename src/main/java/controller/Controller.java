package controller;

import controller.command.*;
import controller.viewHelper.IViewHelper;
import controller.viewHelper.impl.LoginViewHelper;
import controller.viewHelper.impl.model.*;
import controller.viewHelper.impl.model.adm.CartaoDeCreditoAdminViewHelper;
import controller.viewHelper.impl.model.adm.ClienteAdminViewHelper;
import controller.viewHelper.impl.model.adm.CupomAdminViewHelper;
import controller.viewHelper.impl.model.adm.EnderecoAdminViewHelper;
import controller.viewHelper.impl.model.cliente.CartaoDeCreditoViewHelper;
import controller.viewHelper.impl.model.cliente.ClienteViewHelper;
import controller.viewHelper.impl.model.cliente.EnderecoViewHelper;
import model.EntidadeDominio;
import model.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {
        "/login",
        "/logout",
        "/cadastro",
        "/clientes/*",
        "/admin/*"
})
public class Controller extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private Map<String, ICommand> commands;
    private Map<String, IViewHelper> viewHelpers;

    public Controller() {
        commands = new HashMap<>();
        commands.put("salvar", new SalvarCommand());
        commands.put("atualizar", new AtualizarCommand());
        commands.put("listar", new ListarCommand());
        commands.put("remover", new ExcluirCommand());
        commands.put("login", new ListarCommand());
        commands.put("listarTodos", new ListarCommand());
        commands.put("listarAdm", new ListarCommand());

        viewHelpers = new HashMap<>();

        viewHelpers.put("/LootCommerce/cadastro", new ClienteViewHelper());
        viewHelpers.put("/LootCommerce/login", new LoginViewHelper());

        viewHelpers.put("/LootCommerce/clientes/perfil", new ClienteViewHelper());
        viewHelpers.put("/LootCommerce/clientes/perfil/editarSenha", new UsuarioViewHelper());
        viewHelpers.put("/LootCommerce/clientes/cartoes", new CartaoDeCreditoViewHelper());
        viewHelpers.put("/LootCommerce/clientes/enderecos", new EnderecoViewHelper());
        viewHelpers.put("/LootCommerce/clientes/cupons", new CupomAdminViewHelper());

        viewHelpers.put("/LootCommerce/admin/controle", new ClienteAdminViewHelper());
        viewHelpers.put("/LootCommerce/admin/cliente", new ClienteAdminViewHelper());
        viewHelpers.put("/LootCommerce/admin/cartoes", new CartaoDeCreditoAdminViewHelper());
        viewHelpers.put("/LootCommerce/admin/enderecos", new EnderecoAdminViewHelper());
        viewHelpers.put("/LootCommerce/admin/cupons", new CupomAdminViewHelper());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getRequestURI().equals("/LootCommerce/logout")) {
            if(req.getSession().getAttribute("usuarioLogado") != null) {
                req.getSession().invalidate();
            }

            resp.sendRedirect("index.jsp");
            return;
        }

        processaRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processaRequest(req, resp);
    }

    private void processaRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String operacao = req.getParameter("operacao");

        ICommand command = commands.get(operacao);

        IViewHelper viewHelper = viewHelpers.get(req.getRequestURI());

        EntidadeDominio entidade = viewHelper.getEntidade(req);

        Result result;

        result = command.execute(entidade, operacao);

        viewHelper.setView(result, req, resp);
    }

}
