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

        if(dataInicio == null || dataFim == null)
            return "Insira data de início e fim!";

        if(dataInicio.isAfter(dataFim))
            return "A data de inicio não pode ser maior que a data fim";

        LocalDate dataAgora = LocalDate.now();
        if(dataInicio.isAfter(dataAgora))
            return "Data de início não pode ultrapassar o dia atual";

        if(dataFim.isAfter(dataAgora))
            return "Data de fim não pode ultrapassar o dia atual";

        if(dataFim.isAfter(dataInicio.plusYears(1L)))
            return "Período consultado deve respeitar o limite de um ano";

        return null;
    }
}
