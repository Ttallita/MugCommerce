package business.viewHelper.impl.model.venda;

import business.viewHelper.IViewHelper;
import business.viewHelper.impl.model.venda.dashboard.DashboardDataVO;
import business.viewHelper.impl.model.venda.dashboard.DashboardVO;
import com.google.common.collect.Sets;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.carrinho.Carrinho;
import model.carrinho.ItemCarrinho;
import model.cliente.CartaoDeCredito;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import model.cliente.endereco.EnderecoType;
import model.cupom.Cupom;
import model.produto.Produto;
import model.venda.StatusVendaType;
import model.venda.Venda;
import utils.Utils;
import utils.UtilsWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

public class VendaViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        Cliente cliente = new Cliente(usuarioLogado);

        Venda venda = new Venda();

        switch (operacao) {
            case "salvar" -> {
                venda.setCliente(cliente);

                Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");

                String idEndereco = request.getParameter("idEnderecoEscolhido");
                String idEnderecoCobrancaEscolhido = request.getParameter("idEnderecoCobrancaEscolhido");

                if(idEnderecoCobrancaEscolhido == null || idEnderecoCobrancaEscolhido.isBlank()) {
                    idEnderecoCobrancaEscolhido = idEndereco;
                }


                Endereco enderecoEntrega = new Endereco(cliente);
                enderecoEntrega.setId(Long.valueOf(idEndereco));

                Endereco enderecoCobranca = new Endereco(cliente);
                enderecoCobranca.setId(Long.valueOf(idEnderecoCobrancaEscolhido));

                for (String id : request.getParameterValues("idsCartoesSelecionados[]")){
                    CartaoDeCredito cartao = new CartaoDeCredito(cliente);
                    cartao.setId(Long.parseLong(id));

                    venda.addCartaoDeCredito(cartao);
                }

                String[] idsCupons = request.getParameterValues("idsCupons[]");
                if(idsCupons != null) {
                    for (String id : request.getParameterValues("idsCupons[]")){
                        Cupom cupom = new Cupom(cliente);
                        cupom.setId(Long.parseLong(id));

                        venda.addCupom(cupom);
                    }
                }



                venda.setCarrinho(carrinho);
                venda.setEnderecoEntrega(enderecoEntrega);
                venda.setEnderecoCobranca(enderecoCobranca);

                return venda;
            }

            case "listarUnico", "listarJson" -> {
                venda.setCliente(cliente);

                if (request.getRequestURI().contains("finalizarCompra")) {
                    Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
                    String idEnderecoEscolhido = request.getParameter("idEnderecoEscolhido");
                    String idEnderecoCobrancaEscolhido = request.getParameter("idEnderecoCobrancaEscolhido");

                    Endereco enderecoEntrega = new Endereco(cliente);
                    enderecoEntrega.setId(idEnderecoEscolhido != null && !idEnderecoEscolhido.isEmpty() ? Long.parseLong(idEnderecoEscolhido) : null);

                    Endereco enderecoCobranca = new Endereco(cliente);
                    enderecoCobranca.setId(idEnderecoCobrancaEscolhido != null && !idEnderecoCobrancaEscolhido.isEmpty() ? Long.parseLong(idEnderecoCobrancaEscolhido) : null);

                    venda.setEnderecoEntrega(enderecoEntrega);
                    venda.setEnderecoCobranca(enderecoCobranca);
                    venda.setCarrinho(carrinho);
                } else {
                    String idVenda = request.getParameter("id");
                    venda.setId(idVenda == null || idVenda.isEmpty() ? null : Long.parseLong(idVenda));
                }

                return venda;
            }

            case "listar" -> {
                venda.setCliente(cliente);
                return venda;
            }

            case "listarTodos" -> {
                return new Venda();
            }

            case "atualizar" -> {
                String status = request.getParameter("status");
                StatusVendaType tipoStatus = status != null && !status.isEmpty() ? StatusVendaType.valueOf(status) : null;

                String id = request.getParameter("id");
                Long idVenda = id != null && !id.isEmpty() ? Long.parseLong(id) : null;

                venda.setVendaStatus(tipoStatus);
                venda.setId(idVenda);

                return venda;
            }

        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        switch (operacao){

            case "salvar" -> {
                request.getSession().setAttribute("carrinho", null);
                response.sendRedirect("/emug/index.jsp");
            }

            case "atualizar" -> response.sendRedirect("/emug/adm/vendas?operacao=listarTodos");

            case "listarUnico" -> {

                Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

                if (usuarioLogado.isAdministrador()) {

                    Venda venda = (Venda) result.getEntidades().get(0);

                    List<StatusVendaType> proximosStatus = StatusVendaType.getProximoStatus(venda.getVendaStatus());

                    request.setAttribute("proximoStatus", proximosStatus);
                    request.setAttribute("venda", venda);
                    request.setAttribute("listaStatus", StatusVendaType.values());
                    request.getRequestDispatcher("/gerenciar/listarVenda.jsp").forward(request, response);

                } else {

                    Venda venda = (Venda) result.getEntidades().get(0);
                    boolean isEntregaECobranca = venda.getEnderecoEntrega().getTipoEndereco().equals(EnderecoType.COBRANCA_ENTREGA);

                    request.setAttribute("showEnderecoCobranca", isEntregaECobranca);
                    request.setAttribute("enderecoCobranca", venda.getEnderecoCobranca());
                    request.setAttribute("enderecoEntrega", venda.getEnderecoEntrega());
                    request.setAttribute("dataPrevisaoEntrega", Utils.formataLocalDateBR(venda.calculaDataEntrega()));
                    request.setAttribute("carrinho", venda.getCarrinho());
                    request.setAttribute("valorFrete", venda.calculaFrete());

                    String idsCartoesSelecionados = request.getParameter("idsCartoesSelecionados");

                    List<CartaoDeCredito> cartoes = venda.getCartoes();
                    if (idsCartoesSelecionados != null && !idsCartoesSelecionados.isEmpty()) {
                        List<Long> idsCartoes = new ArrayList<>();
                        UtilsWeb.converteParametrosParaLista(idsCartoesSelecionados).forEach(id -> idsCartoes.add(Long.parseLong(id)));

                        cartoes = cartoes.stream().filter(c -> idsCartoes.contains(c.getId())).collect(Collectors.toList());
                    } else {
                        cartoes = cartoes.stream().filter(CartaoDeCredito::isPreferencial).collect(Collectors.toList());
                        idsCartoesSelecionados = cartoes.get(0).getId().toString();
                    }

                    request.setAttribute("idsCartoesSelecionados", idsCartoesSelecionados);
                    request.setAttribute("cartoesSelecionados", cartoes);
                    request.setAttribute("cupons", venda.getCupons());
                    request.setAttribute("venda", venda);

                    request.getRequestDispatcher("/cliente/finalizarCompra.jsp").forward(request, response);
                }
            }

            case "listar" -> {
                List<Venda> vendasOrdenadasDataCompra = ordenaVendasPorDataDeCompra(result.getEntidades());

                request.setAttribute("compras", vendasOrdenadasDataCompra);
                request.getRequestDispatcher("/cliente/compras.jsp").forward(request, response);
            }

            case "listarTodos" -> {
                if(Boolean.parseBoolean(request.getParameter("dashboard"))) {
                    getValoresParaGraficoVolumeVendas(result, request, response);
                    return;
                }

                List<Venda> vendasOrdenadasDataCompra = ordenaVendasPorDataDeCompra(result.getEntidades());

                request.setAttribute("vendas", vendasOrdenadasDataCompra);
                request.getRequestDispatcher("/gerenciar/vendas.jsp").forward(request, response);
            }

            case "listarJson" -> UtilsWeb.montaRespostaJson(result, response);

        }

    }

    private void getValoresParaGraficoVolumeVendas(Result result,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response) throws IOException {

        LocalDate dataInicio = Utils.converteStringLocalDate(request.getParameter("dataInicio"));
        LocalDate dataFim = Utils.converteStringLocalDate(request.getParameter("dataFim"));

        if(dataInicio == null && dataFim == null) {
            dataInicio = LocalDate.of(2022, 10, 1).withDayOfMonth(1);
            dataFim = LocalDate.of(2022, 10, 31).with(TemporalAdjusters.lastDayOfMonth());
        }

        LocalDate finalDataInicio = dataInicio;
        LocalDate finalDataFim = dataFim;

        List<LocalDate> datesBetween = getDatesBetween(dataInicio, dataFim);

        Map<LocalDate, List<Venda>> vendasAgrupadasPorData = result.getEntidades()
                .stream()
                .map(entidade -> (Venda) entidade)
                .filter(venda -> venda.getDataCompra().toLocalDate().isAfter(finalDataInicio) && venda.getDataCompra().toLocalDate().isBefore(finalDataFim))
                .collect(Collectors.groupingBy(venda -> venda.getDataCompra().toLocalDate(), Collectors.toList()));

        Map<LocalDate, Map<Produto, Integer>> mapaQuantidadeProdutosPorData = new TreeMap<>();

        Set<Produto> produtosDoGrafico = new HashSet<>();

        vendasAgrupadasPorData.forEach((k, v) -> {
            Map<Produto, Integer> mapaQuantidadeDeProdutosDaData = new HashMap<>();

            v.forEach(venda -> {
                for (ItemCarrinho itemCarrinho : venda.getCarrinho().getItensCarrinho()) {
                    Integer quantidadeData = mapaQuantidadeDeProdutosDaData.getOrDefault(itemCarrinho.getProduto(), 0);

                    mapaQuantidadeDeProdutosDaData.put(itemCarrinho.getProduto(), itemCarrinho.getQuant() + quantidadeData);

                    produtosDoGrafico.add(itemCarrinho.getProduto());
                }
            });

            mapaQuantidadeProdutosPorData.put(k, mapaQuantidadeDeProdutosDaData);
        });


        Map<Produto, DashboardDataVO> valoresGrafico = new HashMap<>();

        for (LocalDate datasGrafico : datesBetween) {
            Map<Produto, Integer> produtoIntegerMap = mapaQuantidadeProdutosPorData.get(datasGrafico);

            Set<Produto> produtosParaAdicionar = new HashSet<>();
            if(produtoIntegerMap != null) {
                for (Map.Entry<Produto, Integer> entry : produtoIntegerMap.entrySet()) {
                    Produto key = entry.getKey();
                    Integer value = entry.getValue();

                    DashboardDataVO dashboardDataVOS = valoresGrafico.get(key);

                    if(dashboardDataVOS != null)
                        dashboardDataVOS.getData().add(value);
                    else {
                        DashboardDataVO novoData = new DashboardDataVO();
                        novoData.setName(key.getNome());
                        novoData.setData(new ArrayList<>());

                        novoData.getData().add(value);
                        valoresGrafico.put(key, novoData);
                    }
                }

                Set<Produto> produtos = produtoIntegerMap.keySet();

                Sets.SetView<Produto> difference = Sets.difference(produtosDoGrafico, produtos);
                difference.copyInto(produtosParaAdicionar);
            } else {
                produtosParaAdicionar = produtosDoGrafico;
            }

            Collection<DashboardDataVO> dashboardDataVOS = valoresGrafico.values();

            for (Produto produto : produtosParaAdicionar) {
                DashboardDataVO voDashboard = null;

                if (!dashboardDataVOS.isEmpty()) {
                    Optional<DashboardDataVO> optional = dashboardDataVOS.stream()
                            .filter(vo -> vo.getName().equals(produto.getNome()))
                            .findAny();

                    if(optional.isPresent())
                        voDashboard = optional.get();
                }

                if (voDashboard == null) {
                    DashboardDataVO novoData = new DashboardDataVO();
                    novoData.setName(produto.getNome());
                    novoData.setData(new ArrayList<>());

                    novoData.getData().add(0);

                    valoresGrafico.put(produto, novoData);
                } else
                    voDashboard.getData().add(0);
            }
        }

        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");

        ExcludeImageGson excludeImageGson = new ExcludeImageGson();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new UtilsWeb.LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new UtilsWeb.LocalDateTimeSerializer());
        gsonBuilder.addSerializationExclusionStrategy(excludeImageGson);


        Gson gson = gsonBuilder
                .enableComplexMapKeySerialization()
                .create();

        PrintWriter writer = response.getWriter();
        writer.write(gson.toJson(new DashboardVO(datesBetween, valoresGrafico.values().stream().toList())));
        writer.flush();
    }

    public List<Venda> ordenaVendasPorDataDeCompra(List<EntidadeDominio> vendas) {
        return vendas.stream()
                .map(entidade -> (Venda) entidade)
                .sorted(Comparator.comparing(Venda::getDataCompra, Comparator.reverseOrder()))
                .toList();
    }


    private static class ExcludeImageGson implements ExclusionStrategy {
        @Override
        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
            return fieldAttributes.getName().equals("imagem");
        }

        @Override
        public boolean shouldSkipClass(Class<?> aClass) {
            return false;
        }
    }

    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());
    }

}
