package business.facade;

import business.strategy.IStrategy;
import business.strategy.impl.carrinho.VerificaCarrinhoStrategy;
import business.strategy.impl.cliente.*;
import business.strategy.impl.cliente.cartao.VerificarCartaoStrategy;
import business.strategy.impl.dashboard.VerificaDashboardStrategy;
import business.strategy.impl.estoque.VerificaEntradaEstoqueStrategy;
import business.strategy.impl.produto.VerificaProdutoStrategy;
import dao.IDAO;
import dao.UsuarioDAO;
import dao.cliente.CartaoDeCreditoDAO;
import dao.cliente.ClienteDAO;
import dao.cliente.CupomDAO;
import dao.cliente.EnderecoDAO;
import dao.dashboard.DashboardDAO;
import dao.estoque.EstoqueDAO;
import dao.estoque.EstoqueHistoricoDAO;
import dao.produto.*;
import dao.solicitacao.CancelamentoDAO;
import dao.solicitacao.TrocaDAO;
import dao.venda.VendaDAO;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.carrinho.ItemCarrinho;
import model.cliente.CartaoDeCredito;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import model.estoque.Estoque;
import model.estoque.EstoqueHistorico;
import model.cupom.Cupom;
import model.produto.*;
import model.solicitacao.Cancelamento;
import model.solicitacao.Troca;
import model.venda.DashboardVendasAgrupadas;
import model.venda.Venda;
import session.ISessionUtil;
import session.carrinho.CarrinhoSessionUtil;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade implements IFacade {

    private final Map<String, IDAO> daosMap;
    private final Map<String, ISessionUtil> sessionMap;

    private final Map<String, Map<String, List<IStrategy>>> regrasDeNegocioMap;

    public Facade() {
        daosMap = new HashMap<>();
        daosMap.put(Usuario.class.getName(), new UsuarioDAO());
        daosMap.put(Cliente.class.getName(), new ClienteDAO());
        daosMap.put(Endereco.class.getName(), new EnderecoDAO());
        daosMap.put(CartaoDeCredito.class.getName(), new CartaoDeCreditoDAO());
        daosMap.put(Fabricante.class.getName(), new FabricanteDAO());
        daosMap.put(Categoria.class.getName(), new CategoriaDAO());
        daosMap.put(GrupoPrecificacao.class.getName(), new GrupoPrecificacaoDAO());
        daosMap.put(Produto.class.getName(), new ProdutoDAO());
        daosMap.put(ProdutoStatus.class.getName(), new ProdutoStatusDAO());
        daosMap.put(Venda.class.getName(), new VendaDAO());
        daosMap.put(Cupom.class.getName(), new CupomDAO());
        daosMap.put(Troca.class.getName(), new TrocaDAO());
        daosMap.put(Cancelamento.class.getName(), new CancelamentoDAO());
        daosMap.put(Estoque.class.getName(), new EstoqueDAO());
        daosMap.put(EstoqueHistorico.class.getName(), new EstoqueHistoricoDAO());
        daosMap.put(DashboardVendasAgrupadas.class.getName(), new DashboardDAO());


        sessionMap = new HashMap<>();
        sessionMap.put(ItemCarrinho.class.getName(), new CarrinhoSessionUtil());

        regrasDeNegocioMap = new HashMap<>();

        regrasDeNegocioMap.put(Cliente.class.getName(), getRegrasNegocioCliente());
        regrasDeNegocioMap.put(Usuario.class.getName(), getRegrasNegocioUsuario());
        regrasDeNegocioMap.put(Endereco.class.getName(), getRegrasNegocioEndereco());
        regrasDeNegocioMap.put(CartaoDeCredito.class.getName(), getRegrasNegocioCartao());
        regrasDeNegocioMap.put(Produto.class.getName(), getRegrasNegocioProduto());
        regrasDeNegocioMap.put(EstoqueHistorico.class.getName(), getRegrasNegocioEstoqueEntrada());
        regrasDeNegocioMap.put(ItemCarrinho.class.getName(), getRegrasNegocioCarrinho());
        regrasDeNegocioMap.put(DashboardVendasAgrupadas.class.getName(), getRegrasDashboard());
    }

    @Override
    public Result salvar(EntidadeDominio entidade, HttpSession session) {
        Result result = new Result();

        String nomeClasse = entidade.getClass().getName();

        String resultado = executarRegras(entidade, "salvar", nomeClasse);

        if(resultado == null) {
            IDAO dao = daosMap.get(nomeClasse);

            if(dao != null) {
                entidade = dao.salvar(entidade);
            } else {
                ISessionUtil sessionUtil = sessionMap.get(nomeClasse);
                sessionUtil.salvar(entidade, session);
            }
        }

        result.setMsg(resultado);
        result.setEntidades(List.of(entidade));

        return result;
    }

    @Override
    public Result atualizar(EntidadeDominio entidade, HttpSession session) {
        Result result = new Result();

        String nomeClasse = entidade.getClass().getName();

        String resultado = executarRegras(entidade, "atualizar", nomeClasse);

        if(resultado == null) {
            IDAO dao = daosMap.get(nomeClasse);

            if(dao != null) {
                entidade = dao.atualizar(entidade);
            } else {
                ISessionUtil sessionUtil = sessionMap.get(nomeClasse);
                sessionUtil.atualizar(entidade, session);
            }
        }

        result.setMsg(resultado);
        result.setEntidades(List.of(entidade));

        return result;
    }

    @Override
    public Result deletar(EntidadeDominio entidade, HttpSession session) {
        Result result = new Result();

        String nomeClasse = entidade.getClass().getName();

        String resultado = executarRegras(entidade, "remover", nomeClasse);

        if(resultado == null) {
            IDAO dao = daosMap.get(nomeClasse);

            if(dao != null) {
                entidade = dao.deletar(entidade);
            } else {
                ISessionUtil sessionUtil = sessionMap.get(nomeClasse);
                sessionUtil.remover(entidade, session);
            }
        }

        result.setMsg(resultado);
        result.setEntidades(List.of(entidade));

        return result;
    }

    @Override
    public Result listar(EntidadeDominio entidade, HttpSession session, String operacao) {
        Result result = new Result();

        String nomeClasse = entidade.getClass().getName();

        String resultado = executarRegras(entidade, "listar", nomeClasse);

        List<EntidadeDominio> resultadoConsulta = null;

        if(resultado == null) {
            IDAO dao = daosMap.get(nomeClasse);

            resultadoConsulta = dao.listar(entidade, operacao);

            System.err.printf("Consulta (%s - %s) retornou %d resultado(s)\n",
                    entidade.getClass().getSimpleName(), operacao, resultadoConsulta.size());
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

    private Map<String, List<IStrategy>> getRegrasNegocioUsuario() {
        List<IStrategy> regrasAtualizarUsuario = List.of(
                new VerificarSenhaStrategy()
        );

        Map<String, List<IStrategy>> mapaStrategyUsuario = new HashMap<>();
        mapaStrategyUsuario.put("atualizar", regrasAtualizarUsuario);

        return mapaStrategyUsuario;
    }

    private Map<String, List<IStrategy>> getRegrasNegocioEndereco() {
        List<IStrategy> regrasGeraisEndereco = List.of(
                new VerificarEnderecoStrategy()
        );

        Map<String, List<IStrategy>> mapStrategyEndereco = new HashMap<>();
        mapStrategyEndereco.put("salvar", regrasGeraisEndereco);
        mapStrategyEndereco.put("atualizar", regrasGeraisEndereco);

        return mapStrategyEndereco;
    }

    private Map<String, List<IStrategy>> getRegrasNegocioCliente() {
        List<IStrategy> regrasSalvarCliente = List.of(
                new VerificarClienteStrategy(),
                new VerificarCPFStrategy(),
                new VerificarDataStrategy(),
                new VerificarEmailStrategy(),
                new VerificarSenhaStrategy(),
                new VerificarEnderecoStrategy()
        );

        List<IStrategy> regrasAtualizarCliente = List.of(
                new VerificarClienteStrategy(),
                new VerificarCPFStrategy(),
                new VerificarDataStrategy()
        );

        Map<String, List<IStrategy>> mapStrategyCliente = new HashMap<>();
        mapStrategyCliente.put("salvar", regrasSalvarCliente);
        mapStrategyCliente.put("atualizar", regrasAtualizarCliente);
        return mapStrategyCliente;
    }

    private Map<String, List<IStrategy>> getRegrasNegocioCartao() {
        List<IStrategy> regraCartaoGeral = List.of(
                new VerificarCartaoStrategy()
        );

        Map<String, List<IStrategy>> mapStrategyCartao = new HashMap<>();
        mapStrategyCartao.put("salvar", regraCartaoGeral);
        mapStrategyCartao.put("atualizar", regraCartaoGeral);

        return mapStrategyCartao;
    }

    private Map<String, List<IStrategy>> getRegrasNegocioProduto() {
        List<IStrategy> regraProdutoGeral = List.of(
                new VerificaProdutoStrategy()
        );

        Map<String, List<IStrategy>> mapStrategy = new HashMap<>();
        mapStrategy.put("salvar", regraProdutoGeral);
        mapStrategy.put("atualizar", regraProdutoGeral);

        return mapStrategy;
    }

    private Map<String, List<IStrategy>> getRegrasNegocioEstoqueEntrada() {
        List<IStrategy> regraEstoqueGeral = List.of(
                new VerificaEntradaEstoqueStrategy()
        );

        Map<String, List<IStrategy>> mapStrategy = new HashMap<>();
        mapStrategy.put("salvar", regraEstoqueGeral);

        return mapStrategy;
    }

    private Map<String, List<IStrategy>> getRegrasNegocioCarrinho() {
        List<IStrategy> regraCarrinho = List.of(
                new VerificaCarrinhoStrategy()
        );

        Map<String, List<IStrategy>> mapStrategy = new HashMap<>();
        mapStrategy.put("atualizar", regraCarrinho);

        return mapStrategy;
    }

    private Map<String, List<IStrategy>> getRegrasDashboard() {
        List<IStrategy> regraDashboard = List.of(
                new VerificaDashboardStrategy()
        );

        Map<String, List<IStrategy>> mapStrategy = new HashMap<>();
        mapStrategy.put("listar", regraDashboard);

        return mapStrategy;
    }
}

