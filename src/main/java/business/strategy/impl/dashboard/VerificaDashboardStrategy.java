package business.strategy.impl.dashboard;

import business.strategy.IStrategy;
import model.EntidadeDominio;
import model.venda.DashboardVendasAgrupadas;

import java.time.LocalDate;

public class VerificaDashboardStrategy implements IStrategy {
    @Override
    public String processa(EntidadeDominio entidade) {

        DashboardVendasAgrupadas dashboardConsulta = (DashboardVendasAgrupadas) entidade;

        LocalDate dataInicio = dashboardConsulta.getDataInicio();
        LocalDate dataFim = dashboardConsulta.getDataFim();

        if(dataInicio.isAfter(dataFim))
            return "A data de inicio não pode ser maior que a data fim";

        return null;
    }
}
