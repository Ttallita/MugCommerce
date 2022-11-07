package selenium.pageModels.components;

import org.openqa.selenium.WebDriver;
import selenium.pageModels.DashboardPage;
import selenium.pageModels.perfilCliente.PerfilClientePage;
import selenium.utils.UtilsTeste;

public class HeaderAdmComponent extends HeaderComponentAbstract{

    private static final String LINK_DASHBOARD = "/gerenciar/dashboard.jsp";

    public HeaderAdmComponent(WebDriver driver) {
        super(driver);
    }

    public PerfilClientePage acessarPagina(String pagina) {
        return null;
    }

    public DashboardPage acessarDashboard() {
        UtilsTeste.getBotaoByLink(LINK_DASHBOARD, driver).click();
        return new DashboardPage(driver);
    }
}