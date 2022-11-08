package selenium.pageModels.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.pageModels.PageAbstract;
import selenium.utils.UtilsTeste;

import java.util.List;

public class VendaAdmPage extends PageAbstract {

    public static final String TITULO_PAGINA = "Gerenciar - Vendas";

    public VendaAdmPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public DetalhesCompraPage abrirDetalhesCompra(List<String> identificadoresCompra){
        UtilsTeste.findLinhaTabela(driver, identificadoresCompra)
                .findElement(By.tagName("a"))
                .click();

        return new DetalhesCompraPage(driver);
    }

}
