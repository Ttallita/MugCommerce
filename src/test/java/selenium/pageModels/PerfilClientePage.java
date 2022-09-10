package selenium.pageModels;

import org.openqa.selenium.WebDriver;

public class PerfilClientePage extends PageAbstract{

    public static final String TITULO_PAGINA = "Perfil";

    public PerfilClientePage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }
}
