package business.viewHelper.impl.model.estoque;

import business.viewHelper.IViewHelper;
import dao.AuditoriaDAO;
import model.AuditoriaType;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.estoque.Estoque;
import model.estoque.EstoqueHistorico;
import model.estoque.Fornecedor;
import model.produto.Produto;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class EstoqueEntradaViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        if ("salvar".equals(operacao)) {
            EstoqueHistorico estoqueHistorico = new EstoqueHistorico();

            String idProduto = request.getParameter("produto");
            if(!idProduto.isBlank()) {

                Produto produto = new Produto();
                produto.setId(Long.parseLong(idProduto));

                Estoque estoque = new Estoque();
                estoque.setProduto(produto);
                estoqueHistorico.setEstoque(estoque);
            }


            estoqueHistorico.setFornecedor(new Fornecedor(request.getParameter("fornecedor")));
            estoqueHistorico.setDataEntrada(LocalDateTime.now());

            String valorCusto = request.getParameter("valorCusto");
            if(!valorCusto.isBlank())
                estoqueHistorico.setValorCusto(Double.parseDouble(valorCusto));

            String quantidade = request.getParameter("quantidade");
            if(!quantidade.isBlank())
                estoqueHistorico.setQuantidade(Integer.parseInt(quantidade));

            return estoqueHistorico;
        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        List<EntidadeDominio> entidades = result.getEntidades();

        String msgErro = result.getMsg();

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if(operacao.equals("salvar")) {
            if(msgErro == null) {
                new AuditoriaDAO().salvar(Utils.criaAuditoria(entidades.get(0), AuditoriaType.INSERCAO, usuarioLogado));
                response.sendRedirect("/emug/adm/estoque?operacao=listar");
            } else {
                String [] mensagens = msgErro.split("\n");

                request.setAttribute("entrada", entidades.get(0));
                request.setAttribute("mensagens", mensagens);
                request.setAttribute("erro", true);
                request.getRequestDispatcher("/gerenciar/formularios/entradaEstoque.jsp").forward(request, response);
            }
        }
    }
}
