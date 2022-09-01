package selenium.pageModels;

import org.openqa.selenium.WebDriver;

public abstract class PageAbstract {

    protected WebDriver driver;

    public PageAbstract(WebDriver driver, String tituloPagina){
        this.driver = driver;

        if (!driver.getTitle().equals(tituloPagina))
            throw new IllegalStateException(String.format("Esta não é a página de %s. A página atual é: %s",
                                                            tituloPagina, driver.getCurrentUrl()));
    }

}
