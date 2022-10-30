package business.viewHelper.impl.model.solicitacao;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.cliente.Cliente;
import model.produto.Produto;
import model.solicitacao.Troca;
import model.venda.Venda;
import utils.UtilsWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TrocaViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        Troca troca = new Troca(new Cliente(usuarioLogado));

        switch (operacao) {

            case "listar" -> {
                return troca;
            }

            case "listarJson" -> {
                troca.setId(Long.parseLong(request.getParameter("id")));
                return troca;
            }

            case "salvar" -> {
                Venda venda = new Venda();
                Produto produto = new Produto();
                venda.setId(Long.parseLong(request.getParameter("idVenda")));
                produto.setId(Long.parseLong(request.getParameter("idProduto")));

                troca.setVenda(venda);
                troca.setProduto(produto);

                return troca;
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

        }
    }

}
