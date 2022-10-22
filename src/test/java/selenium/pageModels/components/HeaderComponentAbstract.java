package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.pageModels.ResultadoPesquisaPage;
import selenium.utils.UtilsTeste;

public abstract class HeaderComponentAbstract {

    protected WebDriver driver;

    private static final String LINK_INDEX = "/emug/index.jsp";


    public HeaderComponentAbstract(WebDriver driver){
        this.driver = driver;
    }


    public ResultadoPesquisaPage pesquisar(String termoPesquisa){
        WebElement formPesquisa = driver.findElement(By.id("pesquisa"));
        formPesquisa.findElement(By.tagName("input")).sendKeys(termoPesquisa);
        formPesquisa.findElement(By.tagName("button")).click();

        return new ResultadoPesquisaPage(driver);
    }

    public abstract void acessarPagina(String pagina);

    public void acessarHome(){
        UtilsTeste.getBotaoByLink(LINK_INDEX, driver).click();
    }
}
