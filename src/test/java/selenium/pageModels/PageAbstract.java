package selenium.pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.pageModels.components.HeaderAdmComponent;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.pageModels.components.HeaderComponentAbstract;
import selenium.pageModels.components.HeaderUsuarioDeslogado;

public abstract class PageAbstract {

    protected static WebDriver driver;

    public PageAbstract(WebDriver driver, String tituloPagina){
        PageAbstract.driver = driver;

        if (!driver.getTitle().equals(tituloPagina))
            throw new IllegalStateException(String.format("Esta não é a página de %s. A página atual é: %s",
                                                            tituloPagina, driver.getCurrentUrl()));
    }

    public HeaderComponentAbstract getHeader(WebDriver driver) {
        String tipoHeader = driver.findElement(By.tagName("header")).getAttribute("name");

        switch (tipoHeader) {

            case "CLIENTE" -> {
                return new HeaderClienteComponent(driver);
            }

            case "ADMINISTRADOR" -> {
                return new HeaderAdmComponent(driver);
            }

            default -> {
                return new HeaderUsuarioDeslogado(driver);
            }
        }
    }
    

}
