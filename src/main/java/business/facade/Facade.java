package business.facade;

import business.strategy.IStrategy;
import business.strategy.impl.cliente.*;
import dao.ClienteDAO;
import dao.IDAO;
import model.EntidadeDominio;
import model.Result;
import model.cliente.Cliente;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade implements IFacade {

    private final Map<String, IDAO> daosMap;

    private final Map<String, Map<String, List<IStrategy>>> regrasDeNegocioMap;

    public Facade() {
        daosMap = new HashMap<>();
        daosMap.put(Cliente.class.getName(), new ClienteDAO());

        regrasDeNegocioMap = new HashMap<>();

        List<IStrategy> regrasSalvarCliente = List.of(
                new VerificarClienteStrategy(),
                new VerificaCPFStrategy(),
                new VerificarDataStrategy(),
                new VerificarEmailStrategy(),
                new VerificarSenhaStrategy()
        );

        Map<String, List<IStrategy>> mapStrategyCliente = new HashMap<>();
        mapStrategyCliente.put("salvar", regrasSalvarCliente);

        regrasDeNegocioMap.put(Cliente.class.getName(), mapStrategyCliente);
    }

    @Override
    public Result salvar(EntidadeDominio entidade) {
        Result result = new Result();

        String nomeClasse = entidade.getClass().getName();

        String resultado = executarRegras(entidade, "salvar", nomeClasse);

        if(resultado == null) {
            IDAO dao = daosMap.get(nomeClasse);

            entidade = dao.salvar(entidade);
        }

        result.setMsg(resultado);
        result.setEntidades(List.of(entidade));

        return result;
    }

    private String executarRegras(EntidadeDominio entidade, String operacao, String nomeClasse) {
        Map<String, List<IStrategy>> regras = regrasDeNegocioMap.get(nomeClasse);

        var builder = new StringBuilder();

        if(regras != null) {
            List<IStrategy> strategys = regras.get(operacao);

            for (IStrategy strategy : strategys) {
                String resultado = strategy.processa(entidade);

                if(resultado != null) {
                    builder.append(resultado)
                            .append("\n");
                }
            }
        }

        if(builder.length() > 0)
            return builder.toString();

        return null;
    }

    @Override
    public Result atualizar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public Result deletar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public Result listar(EntidadeDominio entidade, String operacao) {
        return null;
    }
}
