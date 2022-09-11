package business.viewHelper.impl.model.cliente;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.cliente.CartaoDeCredito;
import model.cliente.Cliente;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartaoViewHelper implements IViewHelper {
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        switch (operacao) {
            case "listar" -> {
                CartaoDeCredito cartaoDeCredito = new CartaoDeCredito();
                cartaoDeCredito.setCliente(new Cliente(usuarioLogado));
                return cartaoDeCredito;
            }
            case "salvar", "atualizar", "excluir" -> {
                CartaoDeCredito cartao = criaCartao(request);

                String id = request.getParameter("id");
                if (id != null && !id.isBlank())
                    cartao.setId(Long.parseLong(id));

                cartao.setCliente(new Cliente(usuarioLogado));

                return cartao;
            }
            case "listarUnico" -> {
                CartaoDeCredito cartao = new CartaoDeCredito();
                cartao.setId(Long.parseLong(request.getParameter("id")));
                cartao.setCliente(new Cliente(usuarioLogado));

                return cartao;
            }
        }

        return null;
    }

    private CartaoDeCredito criaCartao(HttpServletRequest request) {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito();

        cartaoDeCredito.setNumCartao(request.getParameter("numCartao"));
        cartaoDeCredito.setNomeImpressoCartao(request.getParameter("nomeCartao"));
        cartaoDeCredito.setBandeira(request.getParameter("bandeira"));
        cartaoDeCredito.setPreferencial(request.getParameter("preferencial") != null);

        String dtValidade = request.getParameter("dtValidade");

        if(dtValidade != null && !dtValidade.isBlank()) {
            String[] data = dtValidade.split("/");

            if(data.length > 1) {
                cartaoDeCredito.setMesValidade(Integer.parseInt(data[0]));
                cartaoDeCredito.setAnoValidade(Integer.parseInt(data[1]));
            }
        }

        String codigoCartao = request.getParameter("codigoCartao");
        if(codigoCartao != null && !codigoCartao.isBlank())
            cartaoDeCredito.setCodigo(Integer.parseInt(codigoCartao));
        else
            cartaoDeCredito.setCodigo(null);

        return cartaoDeCredito;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        String msgTela = result.getMsg();
        switch (operacao) {
            case "listar":
                request.setAttribute("cartoes", result.getEntidades());
                request.getRequestDispatcher("/cliente/cartoes.jsp").forward(request, response);
                break;
            case "salvar", "atualizar", "excluir":
                if (msgTela == null)
                    response.sendRedirect("/emug/clientes/cartoes?operacao=listar");
                else {
                    String[] mensagens = msgTela.split("\n");

                    request.setAttribute("mensagens", mensagens);
                    request.setAttribute("erro", true);
                    request.getRequestDispatcher("/formularios/formCartaoCredito.jsp").forward(request, response);
                }
                break;
            case "listarUnico":
                request.setAttribute("isEditar", true);
                request.setAttribute("cartao", result.getEntidades().get(0));
                request.getRequestDispatcher("/formularios/formCartaoCredito.jsp").forward(request, response);
                break;
        }
    }
}
