package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.pageModels.HomePage;
import selenium.pageModels.ResultadoPesquisaPage;
import selenium.pageModels.perfilCliente.PerfilClientePage;
import selenium.utils.UtilsTeste;

public abstract class HeaderComponentAbstract {

    protected WebDriver driver;

    private static final String LINK_INDEX = "/emug/index.jsp";
    private static final String LINK_LOGOUT = "/emug/logout";

    public HeaderComponentAbstract(WebDriver driver){
        this.driver = driver;
    }


    public ResultadoPesquisaPage pesquisar(String termoPesquisa){
        WebElement formPesquisa = driver.findElement(By.id("pesquisa"));
        formPesquisa.findElement(By.tagName("input")).sendKeys(termoPesquisa);
        formPesquisa.findElement(By.tagName("button")).click();

        return new ResultadoPesquisaPage(driver);
    }

    public abstract PerfilClientePage acessarPagina(String pagina);

    public void acessarHome(){
        UtilsTeste.getBotaoByLink(LINK_INDEX, driver).click();
    }

    public HomePage deslogar() {
        UtilsTeste.getBotaoByLink(LINK_LOGOUT, driver).click();
        return new HomePage(driver);
    }
}
