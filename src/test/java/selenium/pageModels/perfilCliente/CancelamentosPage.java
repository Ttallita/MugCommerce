package selenium.pageModels.perfilCliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.pageModels.PageAbstract;
import selenium.pageModels.components.ModalCancelamentosComponent;
import selenium.pageModels.components.SideBarClienteComponent;
import selenium.utils.UtilsTeste;

import java.util.List;

public class CancelamentosPage extends PageAbstract {

    protected SideBarClienteComponent sideBarCliente;

    public static final String TITULO_PAGINA = "Perfil - Cancelamentos";

    public CancelamentosPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);

        sideBarCliente = new SideBarClienteComponent(driver);
    }

    public ModalCancelamentosComponent abrirModalCancelamentos(List<String> identificadoresCompra){
        UtilsTeste.findLinhaTabela(driver, identificadoresCompra)
                .findElement(By.tagName("button"))
                .click();

        return new ModalCancelamentosComponent(driver);
    }

}
