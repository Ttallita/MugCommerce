package selenium.pageModels.perfilCliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.pageModels.PageAbstract;
import selenium.pageModels.components.SideBarClienteComponent;
import selenium.utils.UtilsTeste;

import java.util.List;

public class TrocasPage extends PageAbstract {

    protected SideBarClienteComponent sideBarCliente;

    public static final String TITULO_PAGINA = "Perfil - Trocas";

    public TrocasPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);

        sideBarCliente = new SideBarClienteComponent(driver);
    }

    public void abrirModalTrocas(List<String> identificadoresCompra){
        UtilsTeste.findLinhaTabela(driver, identificadoresCompra)
                .findElement(By.tagName("button"))
                .click();
    }

}
