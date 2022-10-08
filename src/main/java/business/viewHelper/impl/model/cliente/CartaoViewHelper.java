package business.viewHelper.impl.model.cliente;

import business.facade.Facade;
import business.viewHelper.IViewHelper;
import dao.AuditoriaDAO;
import model.AuditoriaType;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.cliente.CartaoDeCredito;
import model.cliente.Cliente;
import utils.Utils;
import utils.UtilsWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class CartaoViewHelper implements IViewHelper {
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        switch (operacao) {
            case "listar", "listarJson" -> {
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

        String origemChamada = request.getParameter("origemChamada");
        if(origemChamada != null)
            UtilsWeb.adicionaParametrosRequestOrigemChamada(origemChamada, request);

        String msgTela = result.getMsg();
        switch (operacao) {
            case "listar" -> {
                List<CartaoDeCredito> cartaoOrdenado = result.getEntidades()
                        .stream()
                        .map(entidade -> (CartaoDeCredito) entidade)
                        .sorted(Comparator.comparing(CartaoDeCredito::isPreferencial).reversed())
                        .toList();

                request.setAttribute("cartoes", cartaoOrdenado);
                request.getRequestDispatcher("/cliente/cartoes.jsp").forward(request, response);
            }
            case "listarJson" ->
                UtilsWeb.montaRespostaJson(result, request, response);
            case "salvar", "atualizar", "excluir" -> {
                if (msgTela == null) {
                    CartaoDeCredito cartao = (CartaoDeCredito) result.getEntidades().get(0);

                    AuditoriaType tipo = operacao.equals("salvar") ? AuditoriaType.INSERCAO : AuditoriaType.ALTERACAO;

                    new AuditoriaDAO().salvar(Utils.criaAuditoria(cartao, tipo, cartao.getCliente().getUsuario()));
                    if(origemChamada != null)
                        UtilsWeb.redirecionarParaOrigemChamada(origemChamada, request, response);
                    else
                        response.sendRedirect("/emug/clientes/cartoes?operacao=listar");

                } else {
                    String[] mensagens = msgTela.split("\n");

                    request.setAttribute("cartao", result.getEntidades().get(0));
                    request.setAttribute("mensagens", mensagens);
                    request.setAttribute("erro", true);
                    request.getRequestDispatcher("/cliente/formularios/formCartaoCredito.jsp").forward(request, response);
                }
            }
            case "listarUnico" -> {
                CartaoDeCredito cartaoPreferencial = (CartaoDeCredito) new Facade().listar(new CartaoDeCredito(), null, "findCartaoPreferencial")
                        .getEntidades()
                        .get(0);

                CartaoDeCredito cartao = (CartaoDeCredito) result.getEntidades().get(0);

                boolean isMostrar = !Objects.equals(cartaoPreferencial.getId(), cartao.getId());

                request.setAttribute("isMostrar", isMostrar);
                request.setAttribute("isEditar", true);
                request.setAttribute("cartao", cartao);
                request.setAttribute("origemChamada", origemChamada != null ? origemChamada : "");
                request.getRequestDispatcher("/cliente/formularios/formCartaoCredito.jsp").forward(request, response);
            }
            case "adicionar" ->
                request.getRequestDispatcher("/cliente/formularios/formCartaoCredito.jsp").forward(request, response);
        }
    }
}
