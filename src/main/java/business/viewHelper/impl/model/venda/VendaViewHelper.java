package business.viewHelper.impl.model.venda;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.carrinho.Carrinho;
import model.cliente.CartaoDeCredito;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import model.venda.Venda;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VendaViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        Cliente cliente = new Cliente(usuarioLogado);

        Venda venda = new Venda();
        venda.setCliente(cliente);

        switch (operacao) {
            case "salvar" -> {
                Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");

                // criar cupons com ids

                // TODO Calcular valor da venda
                venda.setCarrinho(carrinho);

                return venda;
            }

            case "listar" -> {
                Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
                String idEnderecoEscolhido = request.getParameter("idEnderecoEscolhido");

                Endereco enderecoEntrega = new Endereco();
                enderecoEntrega.setId(idEnderecoEscolhido != null && !idEnderecoEscolhido.isEmpty() ? Long.parseLong(idEnderecoEscolhido) : null);

                venda.setEnderecoEntrega(enderecoEntrega);
                venda.setCarrinho(carrinho);
            }

        }

        return venda;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        switch (operacao){

            case "salvar" -> {
                Venda venda = (Venda) result.getEntidades().get(0);

                request.setAttribute("venda", venda);
                request.getRequestDispatcher("/cliente/finalizarCompra.jsp").forward(request, response);
            }

            case "listar" -> {
                Venda venda = (Venda) result.getEntidades().get(0);

                request.setAttribute("enderecoEntrega", venda.getEnderecoEntrega());
                request.setAttribute("carrinho", venda.getCarrinho());
                request.setAttribute("valorFrete", venda.getFrete());

                String idsCartoesSelecionados = request.getParameter("idsCartoesSelecionados");

                List<CartaoDeCredito> cartoes = venda.getCartoes();
                if(idsCartoesSelecionados != null && !idsCartoesSelecionados.isEmpty()){
                    List<Long> idsCartoes = new ArrayList<>();
                    Arrays.stream(idsCartoesSelecionados.replace("[","").replace("]","").split(",")).forEach(idCartao-> idsCartoes.add(Long.parseLong(idCartao)));
                    cartoes = cartoes.stream().filter(c -> idsCartoes.contains(c.getId())).collect(Collectors.toList());
                } else {
                    cartoes = cartoes.stream().filter(CartaoDeCredito::isPreferencial).collect(Collectors.toList());
                }

                request.setAttribute("idsCartoesSelecionados", idsCartoesSelecionados);
                request.setAttribute("cartoesSelecionados", cartoes);

                request.getRequestDispatcher("/cliente/finalizarCompra.jsp").forward(request, response);
            }

        }

    }

}
