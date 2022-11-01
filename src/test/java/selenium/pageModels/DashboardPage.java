package selenium.pageModels;

import org.openqa.selenium.WebDriver;
import selenium.pageModels.components.SideBarAdmComponent;
import selenium.pageModels.dashboard.ProdutoAdmPage;

public class DashboardPage extends PageAbstract{

    private static final String TITULO_PAGINA = "Gerenciar - Dashboard";

    private final SideBarAdmComponent sideBarAdmComponent;

    public DashboardPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);

        sideBarAdmComponent = new SideBarAdmComponent(driver);
    }

    public ProdutoAdmPage acessarProdutos() {
        return sideBarAdmComponent.acessarProdutos();
    }
}
