package selenium.pageModels;

import org.openqa.selenium.WebDriver;

public class HomePage extends PageAbstract{

    private HeaderComponent header;

    public static final String TITULO_PAGINA = "Home";

    public HomePage(WebDriver driver){
        super(driver, TITULO_PAGINA);

        header = new HeaderComponent(driver);
    }

    public boolean isHomeCliente(){
        return header.isHeaderCliente();
    }

}
