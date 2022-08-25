package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serial;

@WebServlet(urlPatterns = {
        "/login",
        "/logout",
        "/cadastro",
        "/clientes/*",
        "/admin/*"
})
public class Controller extends HttpServlet{

    @Serial
    private static final long serialVersionUID = 1L;

    public Controller() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {}
}
