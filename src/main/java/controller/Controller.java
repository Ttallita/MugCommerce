package controller;

import controller.command.*;
import controller.viewHelper.IViewHelper;
import controller.viewHelper.impl.ClienteViewHelper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
//import java.io.Serial;

@WebServlet(urlPatterns = {
        "/login"
})
public class Controller extends HttpServlet{

//    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, ICommand> commandMap;

    private final Map<String, IViewHelper> viewHelperMap;

    public Controller() {
        commandMap = new HashMap<>();
        commandMap.put("salvar", new SalvarCommand());
        commandMap.put("editar", new AtualizarCommand());
        commandMap.put("excluir", new ExcluirCommand());
        commandMap.put("listar", new ListarCommand());
        commandMap.put("login", new ListarCommand());

        viewHelperMap = new HashMap<>();
        viewHelperMap.put("/emug/cadastro.jsp", new ClienteViewHelper());


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {}

    public void processaRequest(HttpServletRequest req, HttpServletRequest res) {

        String operacao = (String) req.getAttribute("operacao");


        ICommand iCommand = commandMap.get(operacao);


        IViewHelper iViewHelper = viewHelperMap.get(res.getPathInfo());

        v

    }
}
