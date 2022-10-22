package business.strategy.impl.cliente.cartao;

import business.strategy.IStrategy;
import model.EntidadeDominio;
import model.cliente.CartaoDeCredito;

import java.time.LocalDate;

public class VerificarCartaoStrategy implements IStrategy {

    @Override
    public String processa(EntidadeDominio entidade) {
        CartaoDeCredito cartao = (CartaoDeCredito) entidade;

        if(cartao.getNumCartao().isBlank())
            return "Insira um número de cartão de crédito válido";

        if(cartao.getBandeira().isBlank())
            return "Insira uma bandeira válida";

        if(cartao.getCodigo() == null)
            return "Insira um código válido";

        if(cartao.getCodigo().toString().length() > 3)
            return "Codigo maior que o esperado";

        if(cartao.getNomeImpressoCartao().isBlank())
            return "Insira um nome para o cartão válido";

        if(cartao.getMesValidade() == null)
            return "Insira um ano de validade válido";

        if(cartao.getAnoValidade() == null)
            return "Insira um mês de validade válido";

        LocalDate dataAtual = LocalDate.now();

        String anoValidade = cartao.getAnoValidade();
        if(anoValidade == null || anoValidade.isEmpty() || (Integer.parseInt(cartao.getAnoValidade()) < dataAtual.getYear()))
            return "Ano de validade menor que o ano atual";

        String mesValidade = cartao.getMesValidade();
        if(mesValidade == null || mesValidade.isEmpty() || (Integer.parseInt(cartao.getMesValidade()) < 1 || Integer.parseInt(cartao.getMesValidade()) > 12))
            return "Mês de validade inválido";

        return null;
    }
}
