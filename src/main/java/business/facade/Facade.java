package business.facade;

import business.strategy.IStrategy;
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
    }

    @Override
    public Result salvar(EntidadeDominio entidade) {
        Result result = new Result();

        String nomeClasse = entidade.getClass().getName();

        IDAO dao = daosMap.get(nomeClasse);

        dao.salvar(entidade);

        return result;
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
