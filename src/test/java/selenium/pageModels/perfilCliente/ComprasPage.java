package selenium.pageModels.perfilCliente;

import org.openqa.selenium.WebDriver;
import selenium.pageModels.PageAbstract;

public class ComprasPage extends PerfilPage {

    public static final String TITULO_PAGINA = "Perfil - Compras";

    public ComprasPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }
}
