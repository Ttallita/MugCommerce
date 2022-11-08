package selenium.pageModels.dashboard;

import org.openqa.selenium.WebDriver;
import selenium.pageModels.PageAbstract;

public class CancelamentosAdmPage extends PageAbstract {

    public static final String TITULO_PAGINA = "Gerenciar - Cancelamentos";

    public CancelamentosAdmPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }
}
