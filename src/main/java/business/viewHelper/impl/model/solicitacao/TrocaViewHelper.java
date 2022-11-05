package business.viewHelper.impl.model.solicitacao;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import model.cliente.endereco.EnderecoType;
import model.produto.Produto;
import model.solicitacao.StatusSolicitacaoType;
import model.solicitacao.Troca;
import model.venda.Venda;
import utils.UtilsWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class TrocaViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Usuario usuarioLogado = getUsuarioLogado(request);

        Troca troca = new Troca(new Cliente(usuarioLogado));

        switch (operacao) {

            case "listar" -> {
                return troca;
            }

            case "listarJson", "listarUnico" -> {
                troca.setId(Long.parseLong(request.getParameter("id")));
                return troca;
            }

            case "listarTodos" -> {
                return new Troca();
            }

            case "salvar" -> {
                Venda venda = new Venda();
                Produto produto = new Produto();
                venda.setId(Long.parseLong(request.getParameter("idVenda")));
                produto.setId(Long.parseLong(request.getParameter("idProduto")));

                troca.setVenda(venda);
                troca.setProduto(produto);
                troca.setQuantidade(Integer.parseInt(request.getParameter("quantTroca")));

                return troca;
            }

            case "atualizar" -> {
                String status = request.getParameter("status");
                StatusSolicitacaoType tipoStatus = status != null && !status.isEmpty() ? StatusSolicitacaoType.valueOf(status) : null;

                String id = request.getParameter("id");
                Long idTroca = id != null && !id.isEmpty() ? Long.parseLong(id) : null;

                String reentradaEstoque = request.getParameter("reentradaEstoque");
                if(reentradaEstoque != null)
                    troca.setReentradaEstoque(reentradaEstoque.equals("on"));

                troca.setStatus(tipoStatus);
                troca.setId(idTroca);

                return troca;
            }

        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        switch (operacao) {

            case "atualizar" -> response.sendRedirect("/emug/adm/trocas?operacao=listarTodos");

            case "listar" -> {
                request.setAttribute("solicitacoes", result.getEntidades());
                request.setAttribute("listaStatus", StatusSolicitacaoType.values());
                request.getRequestDispatcher("/cliente/solicitacoes.jsp").forward(request, response);
            }

            case "listarUnico" -> {
                Troca troca = (Troca) result.getEntidades().get(0);
                request.setAttribute("solicitacao", troca);

                Endereco enderecoEntrega = troca.getVenda().getEnderecoEntrega();
                if (enderecoEntrega.getTipoEndereco().equals(EnderecoType.COBRANCA_ENTREGA)) {
                    request.setAttribute("enderecoCobrancaEntrega", enderecoEntrega);
                } else {
                    request.setAttribute("enderecoEntrega", enderecoEntrega);
                    request.setAttribute("enderecoCobranca", troca.getVenda().getEnderecoCobranca());
                }

                List<StatusSolicitacaoType> proximoStatus = StatusSolicitacaoType.getProximoStatus(troca.getStatus());

                request.setAttribute("proximoStatus", proximoStatus);
                request.setAttribute("listaStatus", StatusSolicitacaoType.values());
                request.getRequestDispatcher("/gerenciar/listarSolicitacao.jsp").forward(request, response);
            }

            case "salvar" -> response.sendRedirect("/emug/clientes/compras?operacao=listar");

            case "listarJson" -> UtilsWeb.montaRespostaJson(result, request, response);

            case "listarTodos" -> {
                List<Troca> trocasOrdenadasPorData = result.getEntidades()
                        .stream()
                        .map(entidade -> (Troca) entidade)
                        .sorted(Comparator.comparing(Troca::getData, Comparator.reverseOrder()))
                        .toList();

                request.setAttribute("solicitacoes", trocasOrdenadasPorData);
                request.getRequestDispatcher("/gerenciar/solicitacoesPendentes.jsp").forward(request, response);
            }
        }
    }

}
