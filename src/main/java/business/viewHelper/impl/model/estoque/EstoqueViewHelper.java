package business.viewHelper.impl.model.estoque;

import business.viewHelper.IViewHelper;
import dao.estoque.EstoqueHistoricoDAO;
import model.EntidadeDominio;
import model.Result;
import model.estoque.Estoque;
import model.estoque.EstoqueHistorico;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class EstoqueViewHelper implements IViewHelper {
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        if(operacao.equals("listar")) {
            return new Estoque();
        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        if(operacao.equals("listar")) {

            List<EntidadeDominio> estoqueHistorico = new EstoqueHistoricoDAO().listar(new EstoqueHistorico(), "listar");

            List<EstoqueHistorico> historicoOrdenadoPorDataEntrada = estoqueHistorico.stream()
                    .map(entidade -> (EstoqueHistorico) entidade)
                    .sorted(Comparator.comparing(EstoqueHistorico::getDataEntrada, Comparator.reverseOrder()))
                    .toList();

            request.setAttribute("estoqueHistorico", historicoOrdenadoPorDataEntrada);
            request.setAttribute("estoques", result.getEntidades());
            request.getRequestDispatcher("/gerenciar/estoque.jsp").forward(request, response);
        }
    }
}
