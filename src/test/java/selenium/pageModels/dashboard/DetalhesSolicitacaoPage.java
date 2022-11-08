package selenium.pageModels.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.pageModels.PageAbstract;

public class DetalhesSolicitacaoPage extends PageAbstract {

    public static final String TITULO_PAGINA = "Solicitação";

    public DetalhesSolicitacaoPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public TrocasAdmPage alterarStatus(String novoStatus){
        driver.findElement(By.id("status")).sendKeys(novoStatus);
        driver.findElement(By.id("botaoAtualizarStatus")).click();

        return new TrocasAdmPage(driver);
    }
}
