package business.viewHelper.impl.model.solicitacao;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import model.cliente.endereco.EnderecoType;
import model.solicitacao.Cancelamento;
import model.solicitacao.StatusSolicitacaoType;
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

            case "listarJson", "listarUnico" -> {
                cancelamento.setId(Long.parseLong(request.getParameter("id")));
                return cancelamento;
            }

            case "salvar" -> {
                Venda venda = new Venda();
                venda.setId(Long.parseLong(request.getParameter("idVenda")));

                cancelamento.setVenda(venda);

                return cancelamento;
            }

            case "atualizar" -> {
                String status = request.getParameter("status");
                StatusSolicitacaoType tipoStatus = status != null && !status.isEmpty() ? StatusSolicitacaoType.valueOf(status) : null;

                String id = request.getParameter("id");
                Long idCancelamento = id != null && !id.isEmpty() ? Long.parseLong(id) : null;

                cancelamento.setStatus(tipoStatus);
                cancelamento.setId(idCancelamento);

                return cancelamento;
            }

        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        switch (operacao) {

            case "atualizar" -> response.sendRedirect("/emug/adm/cancelamentos?operacao=listarTodos");

            case "listar" -> {
                request.setAttribute("solicitacoes", result.getEntidades());
                request.getRequestDispatcher("/cliente/solicitacoes.jsp").forward(request, response);
            }

            case "listarUnico" -> {
                Cancelamento cancelamento = (Cancelamento) result.getEntidades().get(0);
                request.setAttribute("solicitacao", cancelamento);

                Endereco enderecoEntrega = cancelamento.getVenda().getEnderecoEntrega();
                if (enderecoEntrega.getTipoEndereco().equals(EnderecoType.COBRANCA_ENTREGA)) {
                    request.setAttribute("enderecoCobrancaEntrega", enderecoEntrega);
                } else {
                    request.setAttribute("enderecoEntrega", enderecoEntrega);
                    request.setAttribute("enderecoCobranca", cancelamento.getVenda().getEnderecoCobranca());
                }

                request.setAttribute("listaStatus", StatusSolicitacaoType.values());
                request.getRequestDispatcher("/gerenciar/listarSolicitacao.jsp").forward(request, response);
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
