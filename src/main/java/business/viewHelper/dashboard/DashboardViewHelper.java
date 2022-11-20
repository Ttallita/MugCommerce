package business.viewHelper.dashboard;

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
import model.venda.DashboardVendasAgrupadas;
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

public class DashboardViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        DashboardVendasAgrupadas dashboardVendasAgrupadas = new DashboardVendasAgrupadas();

        LocalDate dataInicio = Utils.converteStringLocalDate(request.getParameter("dataInicio"));
        LocalDate dataFim = Utils.converteStringLocalDate(request.getParameter("dataFim"));

        if(dataInicio == null && dataFim == null) {
            dataInicio = LocalDate.of(2022, 10, 1).withDayOfMonth(1);
            dataFim = LocalDate.of(2022, 10, 31).with(TemporalAdjusters.lastDayOfMonth());
        }

        dashboardVendasAgrupadas.setDataInicio(dataInicio);
        dashboardVendasAgrupadas.setDataFim(dataFim);

        return dashboardVendasAgrupadas;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        DashboardVendasAgrupadas requestDashboard = (DashboardVendasAgrupadas) getEntidade(request);

        Map<LocalDate, List<DashboardVendasAgrupadas>> produtoQuantidadePorData = result.getEntidades().stream()
                .map(entidade -> (DashboardVendasAgrupadas) entidade)
                .collect(Collectors.groupingBy(DashboardVendasAgrupadas::getDataCompra));

        Set<String> produtosDoGrafico = result.getEntidades().stream()
                .map(entidade -> ((DashboardVendasAgrupadas) entidade).getNome())
                .collect(Collectors.toSet());

        List<LocalDate> datesBetween = getDatesBetween(requestDashboard.getDataInicio(), requestDashboard.getDataFim());

        Map<String, DashboardDataVO> valoresGrafico = new HashMap<>();

        for (LocalDate datasGrafico : datesBetween) {
            List<DashboardVendasAgrupadas> dashboardVendasAgrupadas = produtoQuantidadePorData.get(datasGrafico);

            Set<String> produtosParaAdicionar = new HashSet<>();
            if(dashboardVendasAgrupadas != null) {

                for (DashboardVendasAgrupadas produtoQuantidade : dashboardVendasAgrupadas) {
                    DashboardDataVO dashboardDataVOS = valoresGrafico.get(produtoQuantidade.getNome());

                    if(dashboardDataVOS != null)
                        dashboardDataVOS.getData().add(produtoQuantidade.getQuantidade());
                    else {
                        DashboardDataVO novoData = new DashboardDataVO();
                        novoData.setName(produtoQuantidade.getNome());
                        novoData.setData(new ArrayList<>());

                        novoData.getData().add(produtoQuantidade.getQuantidade());
                        valoresGrafico.put(produtoQuantidade.getNome(), novoData);
                    }
                }

                Set<String> produtos = dashboardVendasAgrupadas.stream().map(DashboardVendasAgrupadas::getNome).collect(Collectors.toSet());

                Sets.SetView<String> difference = Sets.difference(produtosDoGrafico, produtos);
                difference.copyInto(produtosParaAdicionar);
            } else {
                produtosParaAdicionar = produtosDoGrafico;
            }

            Collection<DashboardDataVO> dashboardDataVOS = valoresGrafico.values();

            for (String produto : produtosParaAdicionar) {
                DashboardDataVO voDashboard = null;

                if (!dashboardDataVOS.isEmpty()) {
                    Optional<DashboardDataVO> optional = dashboardDataVOS.stream()
                            .filter(vo -> vo.getName().equals(produto))
                            .findAny();

                    if(optional.isPresent())
                        voDashboard = optional.get();
                }

                if (voDashboard == null) {
                    DashboardDataVO novoData = new DashboardDataVO();
                    novoData.setName(produto);
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
