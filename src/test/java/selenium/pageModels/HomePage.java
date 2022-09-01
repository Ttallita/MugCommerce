package selenium.pageModels;

import org.openqa.selenium.WebDriver;

public class HomePage extends PageAbstract{

    public static final String TITULO_PAGINA = "Home";

    public HomePage(WebDriver driver){
        super(driver, TITULO_PAGINA);
    }
}
