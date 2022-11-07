package selenium.pageModels.perfilCliente;

import org.openqa.selenium.WebDriver;
import selenium.pageModels.PageAbstract;
import selenium.pageModels.components.SideBarClienteComponent;

public class CancelamentosPage extends PageAbstract {

    protected SideBarClienteComponent sideBarCliente;

    public static final String TITULO_PAGINA = "Perfil - Cancelamentos";

    public CancelamentosPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);

        sideBarCliente = new SideBarClienteComponent(driver);
    }

}
