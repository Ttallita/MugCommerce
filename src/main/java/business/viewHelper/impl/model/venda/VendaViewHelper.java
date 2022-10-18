package business.viewHelper.impl.model.venda;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.carrinho.Carrinho;
import model.cliente.CartaoDeCredito;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import model.cliente.endereco.EnderecoType;
import model.cupom.Cupom;
import model.venda.Venda;
import model.venda.StatusVendaType;
import utils.Utils;
import utils.UtilsWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VendaViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        Cliente cliente = new Cliente(usuarioLogado);

        Venda venda = new Venda();

        switch (operacao) {
            case "salvar" -> {
                venda.setCliente(cliente);

                Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");

                String idEndereco = request.getParameter("idEnderecoEscolhido");
                String idEnderecoCobrancaEscolhido = request.getParameter("idEnderecoCobrancaEscolhido");

                if(idEnderecoCobrancaEscolhido == null || idEnderecoCobrancaEscolhido.isBlank()) {
                    idEnderecoCobrancaEscolhido = idEndereco;
                }


                Endereco enderecoEntrega = new Endereco();
                enderecoEntrega.setId(Long.valueOf(idEndereco));

                Endereco enderecoCobranca = new Endereco();
                enderecoCobranca.setId(Long.valueOf(idEnderecoCobrancaEscolhido));

                List<String> idsCartoesSelecionados = UtilsWeb.converteParametrosParaLista(request.getParameter("idsCartoesSelecionados[]"));

                String cupons = request.getParameter("idsCupons[]");

                List<String> idsCupons = null;
                if(cupons != null)
                    idsCupons = UtilsWeb.converteParametrosParaLista(cupons);

                for (String id : idsCartoesSelecionados){
                    CartaoDeCredito cartao = new CartaoDeCredito();
                    cartao.setId(Long.parseLong(id));

                    venda.addCartaoDeCredito(cartao);
                }

                if(idsCupons != null) {
                    for (String id : idsCupons){
                        Cupom cupom = new Cupom();
                        cupom.setId(Long.parseLong(id));

                        venda.addCupom(cupom);
                    }
                }


                venda.setCarrinho(carrinho);
                venda.setEnderecoEntrega(enderecoEntrega);
                venda.setEnderecoCobranca(enderecoCobranca);

                return venda;
            }

            case "listarUnico", "listarJson" -> {
                venda.setCliente(cliente);

                if (request.getRequestURI().contains("finalizarCompra")) {
                    Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
                    String idEnderecoEscolhido = request.getParameter("idEnderecoEscolhido");
                    String idEnderecoCobrancaEscolhido = request.getParameter("idEnderecoCobrancaEscolhido");

                    Endereco enderecoEntrega = new Endereco();
                    enderecoEntrega.setId(idEnderecoEscolhido != null && !idEnderecoEscolhido.isEmpty() ? Long.parseLong(idEnderecoEscolhido) : null);

                    Endereco enderecoCobranca = new Endereco();
                    enderecoCobranca.setId(idEnderecoCobrancaEscolhido != null && !idEnderecoCobrancaEscolhido.isEmpty() ? Long.parseLong(idEnderecoCobrancaEscolhido) : null);

                    venda.setEnderecoEntrega(enderecoEntrega);
                    venda.setEnderecoCobranca(enderecoCobranca);
                    venda.setCarrinho(carrinho);
                } else {
                    String idVenda = request.getParameter("id");
                    venda.setId(idVenda == null || idVenda.isEmpty() ? null : Long.parseLong(idVenda));
                }

                return venda;
            }

            case "listar" -> {
                venda.setCliente(cliente);
                return venda;
            }

            case "listarTodos" -> {
                return new Venda();
            }

            case "atualizar" -> {
                String status = request.getParameter("status");
                StatusVendaType tipoStatus = status != null && !status.isEmpty() ? StatusVendaType.valueOf(status) : null;

                String id = request.getParameter("id");
                Long idVenda = id != null && !id.isEmpty() ? Long.parseLong(id) : null;

                venda.setVendaStatus(tipoStatus);
                venda.setId(idVenda);

                return venda;
            }

        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        switch (operacao){

            case "salvar" -> response.sendRedirect("/emug/index.jsp");

            case "atualizar" -> response.sendRedirect("/emug/adm/vendas?operacao=listarTodos");

            case "listarUnico" -> {

                Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

                if (usuarioLogado.isAdministrador()) {
                    request.setAttribute("venda", result.getEntidades().get(0));
                    request.setAttribute("listaStatus", StatusVendaType.values());
                    request.getRequestDispatcher("/gerenciar/listarVenda.jsp").forward(request, response);

                } else {

                    Venda venda = (Venda) result.getEntidades().get(0);
                    boolean isEntregaECobranca = venda.getEnderecoEntrega().getTipoEndereco().equals(EnderecoType.COBRANCA_ENTREGA);

                    request.setAttribute("showEnderecoCobranca", isEntregaECobranca);
                    request.setAttribute("enderecoCobranca", venda.getEnderecoCobranca());
                    request.setAttribute("enderecoEntrega", venda.getEnderecoEntrega());
                    request.setAttribute("dataPrevisaoEntrega", Utils.formataLocalDateBR(venda.calculaDataEntrega()));
                    request.setAttribute("carrinho", venda.getCarrinho());
                    request.setAttribute("valorFrete", venda.calculaFrete());

                    String idsCartoesSelecionados = request.getParameter("idsCartoesSelecionados");

                    List<CartaoDeCredito> cartoes = venda.getCartoes();
                    if (idsCartoesSelecionados != null && !idsCartoesSelecionados.isEmpty()) {
                        List<Long> idsCartoes = new ArrayList<>();
                        UtilsWeb.converteParametrosParaLista(idsCartoesSelecionados).forEach(id -> idsCartoes.add(Long.parseLong(id)));

                        cartoes = cartoes.stream().filter(c -> idsCartoes.contains(c.getId())).collect(Collectors.toList());
                    } else {
                        cartoes = cartoes.stream().filter(CartaoDeCredito::isPreferencial).collect(Collectors.toList());
                        idsCartoesSelecionados = cartoes.get(0).getId().toString();
                    }

                    request.setAttribute("idsCartoesSelecionados", idsCartoesSelecionados);
                    request.setAttribute("cartoesSelecionados", cartoes);
                    request.setAttribute("cupons", venda.getCupons());

                    request.getRequestDispatcher("/cliente/finalizarCompra.jsp").forward(request, response);
                }
            }

            case "listar" -> {
                request.setAttribute("compras", result.getEntidades());
                request.getRequestDispatcher("/cliente/compras.jsp").forward(request, response);
            }

            case "listarTodos" -> {
                request.setAttribute("vendas", result.getEntidades());
                request.getRequestDispatcher("/gerenciar/vendas.jsp").forward(request, response);
            }

            case "listarJson" -> UtilsWeb.montaRespostaJson(result, request, response);

        }

    }

}
