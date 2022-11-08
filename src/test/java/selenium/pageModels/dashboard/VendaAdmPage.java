package selenium.pageModels.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.pageModels.PageAbstract;

import java.util.List;

public class VendaAdmPage extends PageAbstract {

    public static final String TITULO_PAGINA = "Gerenciar - Vendas";

    public VendaAdmPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public void acessaTelaStatusVenda() {
        WebElement linhaVenda = driver.findElements(By.tagName("tr")).get(1);

        List<WebElement> colunasVenda = linhaVenda.findElements(By.tagName("td"));

        WebElement webElement = colunasVenda.get(colunasVenda.size() - 1);
        WebElement linkListarVenda = webElement.findElement(By.tagName("a"));

        linkListarVenda.click();
    }


}
