package business.viewHelper.impl.model.solicitacao;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.cliente.Cliente;
import model.solicitacao.Cancelamento;
import model.venda.Venda;
import utils.UtilsWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelamentoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        Cancelamento cancelamento = new Cancelamento(new Cliente(usuarioLogado));

        switch (operacao) {
            case "listar" -> {
                return cancelamento;
            }

            case "listarTodos" -> {
                return new Cancelamento();
            }

            case "listarJson" -> {
                cancelamento.setId(Long.parseLong(request.getParameter("id")));
                return cancelamento;
            }

            case "salvar" -> {
                Venda venda = new Venda();
                venda.setId(Long.parseLong(request.getParameter("idVenda")));

                cancelamento.setVenda(venda);

                return cancelamento;
            }
        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        switch (operacao) {
            case "listar" -> {
                request.setAttribute("solicitacoes", result.getEntidades());
                request.getRequestDispatcher("/cliente/solicitacoes.jsp").forward(request, response);
            }

            case "salvar" ->
                    response.sendRedirect("/emug/clientes/compras?operacao=listar");

            case "listarJson" -> UtilsWeb.montaRespostaJson(result, request, response);

            case "listarTodos" -> {
                request.setAttribute("solicitacoes", result.getEntidades());
                request.getRequestDispatcher("/gerenciar/solicitacoesPendentes.jsp").forward(request, response);
            }


        }
    }

}
