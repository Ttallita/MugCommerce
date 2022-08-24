package controller.facade;

import controller.strategy.IStrategy;
import controller.strategy.impl.cartao.ValidarCartaoStrategy;
import controller.strategy.impl.cliente.*;
import controller.strategy.impl.endereco.VerificarEnderecoStrategy;
import dao.*;
import dao.cliente.CartaoDeCreditoDAO;
import dao.cliente.ClienteDAO;
import dao.cliente.EnderecoDAO;
import model.Result;
import model.EntidadeDominio;
import model.Usuario;
import model.cliente.CartaoDeCredito;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import model.cupom.Cupom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade implements IFacade {

    private Map<String, IDAO> daosMap;
    private Map<String, Map<String, List<IStrategy>>> regrasDeNegocioMap;

    private Result result;

    public Facade() {
        daosMap = new HashMap<>();
        daosMap.put(Cliente.class.getName(), new ClienteDAO());
        daosMap.put(CartaoDeCredito.class.getName(), new CartaoDeCreditoDAO());
        daosMap.put(Cupom.class.getName(), new CupomDAO());
        daosMap.put(Endereco.class.getName(), new EnderecoDAO());
        daosMap.put(Usuario.class.getName(), new UsuarioDAO());

        regrasDeNegocioMap = new HashMap<>();

        // Regras de validação
        ValidarDataStrategy validarDataStrategy = new ValidarDataStrategy();
        VerificarClienteStrategy verificarClienteStrategy = new VerificarClienteStrategy();
        VerificarCpfStrategy verificarCpfStrategy = new VerificarCpfStrategy();
        VerificarEmailStrategy verificarEmailStrategy = new VerificarEmailStrategy();
        VerificarSenhaStrategy verificarSenhaStrategy = new VerificarSenhaStrategy();
        VerificarEnderecoStrategy verificarEnderecoStrategy = new VerificarEnderecoStrategy();
        ValidarCartaoStrategy validarCartaoStrategy = new ValidarCartaoStrategy();

        //Lista de regras de validação do cliente
        List<IStrategy> regraDeNegocioSalvarCliente = new ArrayList<>();
        List<IStrategy> regraDeNegocioAtualizarCliente = new ArrayList<>();

        //Lista de regras de validação de usuario
        List<IStrategy> regraDeNegocioAtualizarUsuario = new ArrayList<>();

        //Lista de regras de validação do endereço
        List<IStrategy> regraDeNegocioSalvarEndereco = new ArrayList<>();
        List<IStrategy> regraDeNegocioAtualizarEndereco = new ArrayList<>();

        //Lista de regraas de validação de cartão
        List<IStrategy> regraDeNegocioSalvarCartao = new ArrayList<>();
        List<IStrategy> regraDeNegocioAtualziarCartao = new ArrayList<>();

        regraDeNegocioSalvarCliente.add(validarDataStrategy);
        regraDeNegocioSalvarCliente.add(verificarClienteStrategy);
        regraDeNegocioSalvarCliente.add(verificarCpfStrategy);
        regraDeNegocioSalvarCliente.add(verificarEmailStrategy);
        regraDeNegocioSalvarCliente.add(verificarSenhaStrategy);
        regraDeNegocioSalvarCliente.add(verificarEnderecoStrategy);

        regraDeNegocioAtualizarCliente.add(validarDataStrategy);
        regraDeNegocioAtualizarCliente.add(verificarClienteStrategy);
        regraDeNegocioAtualizarCliente.add(verificarCpfStrategy);

        regraDeNegocioAtualizarUsuario.add(verificarEmailStrategy);
        regraDeNegocioAtualizarUsuario.add(verificarSenhaStrategy);

        regraDeNegocioSalvarEndereco.add(verificarEnderecoStrategy);
        regraDeNegocioAtualizarEndereco.add(verificarEnderecoStrategy);

        regraDeNegocioSalvarCartao.add(validarCartaoStrategy);
        regraDeNegocioAtualziarCartao.add(validarCartaoStrategy);

        //Mapa das regras de négocio do cliente por operação
        Map<String, List<IStrategy>> regrasDeNegocioCliente = new HashMap<>();
        regrasDeNegocioCliente.put("salvar", regraDeNegocioSalvarCliente);
        regrasDeNegocioCliente.put("atualizar", regraDeNegocioAtualizarCliente);

        //Mapa regra de négocio de usuario
        Map<String, List<IStrategy>> regrasDeNegocioUsuario = new HashMap<>();
        regrasDeNegocioUsuario.put("atualizar", regraDeNegocioAtualizarUsuario);

        //Mapa regra de négocio de endereco
        Map<String, List<IStrategy>> regrasDeNegocioEndereco = new HashMap<>();
        regrasDeNegocioEndereco.put("salvar", regraDeNegocioSalvarEndereco);
        regrasDeNegocioEndereco.put("atualizar", regraDeNegocioAtualizarEndereco);

        //Mapa regra de négocio de cartao
        Map<String, List<IStrategy>> regraDeNegocioCartao = new HashMap<>();
        regraDeNegocioCartao.put("salvar", regraDeNegocioSalvarCartao);
        regraDeNegocioCartao.put("atualizar", regraDeNegocioAtualziarCartao);

        regrasDeNegocioMap.put(Cliente.class.getName(), regrasDeNegocioCliente);
        regrasDeNegocioMap.put(Usuario.class.getName(), regrasDeNegocioUsuario);
        regrasDeNegocioMap.put(Endereco.class.getName(), regrasDeNegocioEndereco);
        regrasDeNegocioMap.put(CartaoDeCredito.class.getName(), regraDeNegocioCartao);
    }


    @Override
    public Result salvar(EntidadeDominio entidade) {
        result = new Result();

        String classe = entidade.getClass().getName();

        String msgValidacao = validarRegrasDeNegocio(entidade, "salvar");

        List<EntidadeDominio> entidades = new ArrayList<>();
        entidades.add(entidade);

        result.setEntidades(entidades);

        if(msgValidacao == null) {
            IDAO dao = daosMap.get(classe);

            dao.salvar(entidade);
        } else {
            result.setMsg(msgValidacao);
        }

        return result;

    }

    @Override
    public Result atualizar(EntidadeDominio entidade) {

        result = new Result();

        String classe = entidade.getClass().getName();

        String msgValidacao = validarRegrasDeNegocio(entidade, "atualizar");

        if(msgValidacao == null) {
            IDAO dao = daosMap.get(classe);


            EntidadeDominio entidadeDominio = dao.atualizar(entidade);

            if(entidadeDominio != null) {
                List<EntidadeDominio> entidades = new ArrayList<>();
                entidades.add(entidadeDominio);

                result.setEntidades(entidades);

            } else {
                String msgErro = "Erro ao atualizar registro";
                result.setMsg(msgErro);
            }
        } else {
            result.setMsg(msgValidacao);
        }

        return result;

    }

    @Override
    public Result deletar(EntidadeDominio entidade) {
        result = new Result();

        String nomeClasse = entidade.getClass().getName();

        String msgValidacao = validarRegrasDeNegocio(entidade, "remover");

        if(msgValidacao == null) {
            IDAO idao = daosMap.get(nomeClasse);


            EntidadeDominio entidadeDominio = idao.deletar(entidade);

            if(entidadeDominio != null) {
                List<EntidadeDominio> entidades = new ArrayList<>();
                entidades.add(entidadeDominio);

                result.setEntidades(entidades);

            } else {
                String msgErro = "Erro ao remover registro";
                result.setMsg(msgErro);
            }

            return result;
        } else {
            result.setMsg(msgValidacao);
        }
        return null;
    }

    @Override
    public Result listar(EntidadeDominio entidade, String operacao) {
        result = new Result();

        String nomeClasse = entidade.getClass().getName();

        String msgValidacao = validarRegrasDeNegocio(entidade, operacao);

        if(msgValidacao == null) {
            IDAO dao = daosMap.get(nomeClasse);

            List<EntidadeDominio> listaEntidades = dao.listar(entidade, operacao);

            result.setEntidades(listaEntidades);
        } else {
            result.setMsg(msgValidacao);
        }

        return result;
    }
    
    public String validarRegrasDeNegocio(EntidadeDominio entidadeDominio, String operacao) {
        String nomeClasse = entidadeDominio.getClass().getName();

        StringBuilder msg = new StringBuilder();

        Map<String, List<IStrategy>> regrasDeNegocio = regrasDeNegocioMap.get(nomeClasse);

        if(regrasDeNegocio != null) {
            List<IStrategy> regras = regrasDeNegocio.get(operacao);

            if(regras != null) {
                for (IStrategy strategy : regras) {
                    String msgRegra = strategy.processa(entidadeDominio);
                    if(msgRegra != null) {
                        msg.append(msgRegra + "\n");
                    }
                }
            }
        }

        if(msg.length() > 0) {
            return msg.toString();
        } else {
            return null;
        }
    }
}
