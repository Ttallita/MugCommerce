package selenium.pageModels.perfilCliente;

import org.openqa.selenium.WebDriver;
import selenium.pageModels.PageAbstract;

public class AtualizarSenhaPage extends PerfilPage {

    public static final String TITULO_PAGINA = "Perfil - Atualizar senha";

    public AtualizarSenhaPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }
}
