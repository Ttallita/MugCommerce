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

                venda.setCarrinho(carrinho);

                return venda;
            }

            case "listar" -> {
                Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
                String idCartaoSelecionado = request.getParameter("idCartaoDeCredito");
                String idEnderecoEscolhido = request.getParameter("idEndereco");

                Endereco enderecoEntrega = new Endereco();
                CartaoDeCredito cartao = new CartaoDeCredito();

                enderecoEntrega.setId(idEnderecoEscolhido != null && !idEnderecoEscolhido.isEmpty() ? Long.parseLong(idEnderecoEscolhido) : null);
                cartao.setId(idCartaoSelecionado != null && !idCartaoSelecionado.isEmpty()? Long.parseLong(idCartaoSelecionado) : null);

                venda.setEnderecoEntrega(enderecoEntrega);
                venda.setCartao(cartao);
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
                request.setAttribute("cartaoSelecionado", venda.getCartao());
                request.setAttribute("carrinho", venda.getCarrinho());

                request.getRequestDispatcher("/cliente/finalizarCompra.jsp").forward(request, response);
            }

        }

    }

}
