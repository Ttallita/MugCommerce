package selenium.pageModels.perfilCliente;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import selenium.pageModels.PageAbstract;

public class ComprasPage extends PageAbstract {

    public static final String TITULO_PAGINA = "Perfil - Compras";

    public ComprasPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public void abrirModalCompraById(String idCompra){

    }
}
