package selenium.pageModels;

import org.openqa.selenium.WebDriver;

public class ResultadoPesquisaPage extends PageAbstract {

    public static final String TITULO_PAGINA = "Resultado pesquisa";

    public ResultadoPesquisaPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }
}
