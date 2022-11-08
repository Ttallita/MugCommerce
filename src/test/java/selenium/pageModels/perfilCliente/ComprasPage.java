package selenium.pageModels.perfilCliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.pageModels.PageAbstract;
import selenium.utils.UtilsTeste;

import java.util.List;

public class ComprasPage extends PageAbstract {

    public static final String TITULO_PAGINA = "Perfil - Compras";

    public ComprasPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public void abrirModalCompra(List<String> identificadoresCompra){
        UtilsTeste.findLinhaTabela(driver, identificadoresCompra)
                .findElement(By.tagName("button"))
                .click();
    }
}
