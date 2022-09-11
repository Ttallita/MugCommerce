package selenium.pageModels.components;

import org.openqa.selenium.WebDriver;

public abstract class HeaderComponentAbstract {

    protected WebDriver driver;

    public HeaderComponentAbstract(WebDriver driver){
        this.driver = driver;
    }

    public abstract void acessarPagina(String pagina);
}
