package selenium.pageModels.perfilCliente;

import org.openqa.selenium.WebDriver;
import selenium.pageModels.PageAbstract;

public class CuponsPage extends PerfilPage {

    public static final String TITULO_PAGINA = "Perfil - Cupons";

    public CuponsPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

}
