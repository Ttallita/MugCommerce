package business.viewHelper.impl.model.venda;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.cliente.Cliente;
import model.produto.Produto;
import model.venda.Venda;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VendaViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        Cliente cliente = new Cliente();
        cliente.setUsuario(usuarioLogado);

        Venda venda = new Venda();
        venda.setCliente(cliente);

//        request.getParameter("carrinho");
//        venda.setProdutos(produtos);

        return venda;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        if ("listar".equals(operacao)) {
            Venda venda = (Venda) result.getEntidades().get(0);

            request.setAttribute("enderecoEntrega", venda.getEnderecoEntrega());
            request.setAttribute("cartaoCredito", venda.getCartoesdeCreditos().get(0));
            request.setAttribute("cupons", venda.getCupons());
            request.setAttribute("produtos", venda.getProdutos());
            request.setAttribute("valorItens", venda.getProdutos());

            request.getRequestDispatcher("/cliente/finalizarCompra.jsp").forward(request, response);
        }
    }

}
