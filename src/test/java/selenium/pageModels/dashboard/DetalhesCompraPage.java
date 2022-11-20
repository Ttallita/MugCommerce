package selenium.pageModels.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.pageModels.PageAbstract;

public class DetalhesCompraPage extends PageAbstract {

    public static final String TITULO_PAGINA = "Venda";

    public DetalhesCompraPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public VendaAdmPage alterarStatus(String novoStatus) throws InterruptedException {
        driver.findElement(By.id("status")).sendKeys(novoStatus);

        Thread.sleep(1000);

        driver.findElement(By.id("botaoAtualizarStatus")).click();

        return new VendaAdmPage(driver);
    }

}
