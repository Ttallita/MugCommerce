package business.strategy.impl.estoque;

import business.strategy.IStrategy;
import model.EntidadeDominio;
import model.estoque.EstoqueHistorico;

public class VerificaEntradaEstoqueStrategy implements IStrategy {
    @Override
    public String processa(EntidadeDominio entidade) {
        EstoqueHistorico entrada = (EstoqueHistorico) entidade;

        if(entrada.getEstoque() == null)
            return "Escolha um produto";

        if(entrada.getQuantidade() == null || entrada.getQuantidade() <= 0)
            return "A quantidade deve ser maio que 0";

        if(entrada.getFornecedor().getNome().isBlank())
            return "Escolha um fornecedor";

        if(entrada.getValorCusto() == null)
            return "Escolha um valor de custo";

        return null;
    }
}
