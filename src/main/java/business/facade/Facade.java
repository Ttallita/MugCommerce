package business.facade;

import business.strategy.IStrategy;
import business.strategy.impl.cliente.*;
import dao.ClienteDAO;
import dao.EnderecoDAO;
import dao.IDAO;
import dao.UsuarioDAO;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade implements IFacade {

    private final Map<String, IDAO> daosMap;

    private final Map<String, Map<String, List<IStrategy>>> regrasDeNegocioMap;

    public Facade() {
        daosMap = new HashMap<>();
        daosMap.put(Usuario.class.getName(), new UsuarioDAO());
        daosMap.put(Cliente.class.getName(), new ClienteDAO());
        daosMap.put(Endereco.class.getName(), new EnderecoDAO());

        regrasDeNegocioMap = new HashMap<>();

        List<IStrategy> regrasSalvarCliente = List.of(
                new VerificarClienteStrategy(),
                new VerificaCPFStrategy(),
                new VerificarDataStrategy(),
                new VerificarEmailStrategy(),
                new VerificarSenhaStrategy()
        );

        List<IStrategy> regrasAtualizarCliente = List.of(
                new VerificarClienteStrategy(),
                new VerificaCPFStrategy(),
                new VerificarDataStrategy()
        );


        Map<String, List<IStrategy>> mapStrategyCliente = new HashMap<>();
        mapStrategyCliente.put("salvar", regrasSalvarCliente);
        mapStrategyCliente.put("atualizar", regrasAtualizarCliente);

        List<IStrategy> regrasAtualizarUsuario = List.of(
                new VerificarSenhaStrategy()
        );

        Map<String, List<IStrategy>> mapaStrategyUsuario = new HashMap<>();
        mapaStrategyUsuario.put("atualizar", regrasAtualizarUsuario);

        regrasDeNegocioMap.put(Cliente.class.getName(), mapStrategyCliente);
        regrasDeNegocioMap.put(Usuario.class.getName(), mapaStrategyUsuario);
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

    @Override
    public Result atualizar(EntidadeDominio entidade) {
        Result result = new Result();

        String nomeClasse = entidade.getClass().getName();

        String resultado = executarRegras(entidade, "atualizar", nomeClasse);

        if(resultado == null) {
            IDAO dao = daosMap.get(nomeClasse);

            entidade = dao.atualizar(entidade);
        }

        result.setMsg(resultado);
        result.setEntidades(List.of(entidade));

        return result;
    }

    @Override
    public Result deletar(EntidadeDominio entidade) {
        Result result = new Result();

        String nomeClasse = entidade.getClass().getName();

        String resultado = executarRegras(entidade, "remover", nomeClasse);

        if(resultado == null) {
            IDAO dao = daosMap.get(nomeClasse);

            entidade = dao.deletar(entidade);
        }

        result.setMsg(resultado);
        result.setEntidades(List.of(entidade));

        return result;
    }

    @Override
    public Result listar(EntidadeDominio entidade, String operacao) {
        Result result = new Result();

        String nomeClasse = entidade.getClass().getName();

        String resultado = executarRegras(entidade, "listar", nomeClasse);

        List<EntidadeDominio> resultadoConsulta = null;

        if(resultado == null) {
            IDAO dao = daosMap.get(nomeClasse);

            resultadoConsulta = dao.listar(entidade, operacao);
        }

        result.setMsg(resultado);
        result.setEntidades(resultadoConsulta);

        return result;

    }

    private String executarRegras(EntidadeDominio entidade, String operacao, String nomeClasse) {
        Map<String, List<IStrategy>> regras = regrasDeNegocioMap.get(nomeClasse);

        var builder = new StringBuilder();

        if(regras != null) {
            List<IStrategy> strategys = regras.get(operacao);

            if(strategys != null) {
                for (IStrategy strategy : strategys) {
                    String resultado = strategy.processa(entidade);

                    if(resultado != null) {
                        builder.append(resultado)
                                .append("\n");
                    }
                }
            }
        }

        if(builder.length() > 0)
            return builder.toString();

        return null;
    }
}
