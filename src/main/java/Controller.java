import business.command.*;
import business.viewHelper.IViewHelper;
import business.viewHelper.impl.model.adm.ClienteAdmViewHelper;
import business.viewHelper.impl.model.cliente.ClienteViewHelper;
import business.viewHelper.impl.LoginViewHelper;
import business.viewHelper.impl.model.UsuarioViewHelper;
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
import java.io.Serial;

@WebServlet(urlPatterns = {
        "/login",
        "/cadastro",
        "/clientes/*",
        "/adm/*"
})
public class Controller extends HttpServlet{

    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, AbstractCommand> commandMap;

    private final Map<String, IViewHelper> viewHelperMap;

    public Controller() {
        commandMap = new HashMap<>();
        commandMap.put("salvar", new SalvarCommand());
        commandMap.put("atualizar", new AtualizarCommand());
        commandMap.put("excluir", new ExcluirCommand());
        commandMap.put("listar", new ListarCommand());
        commandMap.put("login", new ListarCommand());
        commandMap.put("listarTodos", new ListarCommand());

        viewHelperMap = new HashMap<>();
        viewHelperMap.put("/emug/cadastro", new ClienteViewHelper());
        viewHelperMap.put("/emug/login", new LoginViewHelper());
        viewHelperMap.put("/emug/clientes", new ClienteViewHelper());
        viewHelperMap.put("/emug/clientes/atualizarSenha", new UsuarioViewHelper());

        viewHelperMap.put("/emug/adm/clientes", new ClienteAdmViewHelper());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(req.getRequestURI().equals("/emug/logout")) {
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

    public void processaRequest(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");

        String operacao = req.getParameter("operacao");

        AbstractCommand command = commandMap.get(operacao);

        IViewHelper viewHelper = viewHelperMap.get(req.getRequestURI());

        EntidadeDominio entidade = viewHelper.getEntidade(req);

        Result result = command.execute(entidade, operacao);

        viewHelper.setView(result, req, res);
    }
}
