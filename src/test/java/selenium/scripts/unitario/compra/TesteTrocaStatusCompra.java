package selenium.scripts.unitario.compra;

import org.junit.jupiter.api.Test;
import selenium.pageModels.DashboardPage;
import selenium.pageModels.HomePage;
import selenium.pageModels.components.HeaderAdmComponent;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.pageModels.dashboard.VendaAdmPage;
import selenium.scripts.TesteAbstract;
import selenium.service.TesteCompraService;

public class TesteTrocaStatusCompra extends TesteAbstract {

    private HeaderClienteComponent header;

    @Override
    protected void configurarCenarioTeste() {
        // HomePage homePage = realizarLoginClientePadrao();
        // header = (HeaderClienteComponent) homePage.getHeader(driver);
    }

    @Test
    public void alteraStatusCompra() throws InterruptedException {
        // TesteCompraService.realizarCompra(driver, header);
//        header.deslogar();

        HomePage homePage = realizarLoginAdmPadrao();
        HeaderAdmComponent headerAdm = (HeaderAdmComponent) homePage.getHeader(driver);

        DashboardPage dashboardPage = headerAdm.acessarDashboard();
        VendaAdmPage vendaAdmPage = dashboardPage.acessarVendas();

        // Pega a primeira venda para entrar na tela de status
        vendaAdmPage.acessaTelaStatusVenda();

    }
}
