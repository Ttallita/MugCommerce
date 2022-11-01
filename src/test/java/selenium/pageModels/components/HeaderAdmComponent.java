package selenium.pageModels.components;

import org.openqa.selenium.WebDriver;
import selenium.pageModels.DashboardPage;
import selenium.utils.UtilsTeste;

public class HeaderAdmComponent extends HeaderComponentAbstract{

    private static final String LINK_DASHBOARD = "/gerenciar/dashboard.jsp";

    public HeaderAdmComponent(WebDriver driver) {
        super(driver);
    }

    @Override
    public void acessarPagina(String pagina) {}

    public DashboardPage acessarDashboard() {
        UtilsTeste.getBotaoByLink(LINK_DASHBOARD, driver).click();
        return new DashboardPage(driver);
    }
}