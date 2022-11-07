package selenium.pageModels.perfilCliente;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.page.Page;
import selenium.pageModels.PageAbstract;

public class AtualizarSenhaPage extends PageAbstract {

    public static final String TITULO_PAGINA = "Perfil - Atualizar senha";

    public AtualizarSenhaPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }
}
