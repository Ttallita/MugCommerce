package selenium.pageModels.perfilCliente;

import org.openqa.selenium.WebDriver;

public class PerfilPrincipalPage extends PerfilPage {

    private static final String TITULO_PAGINA = "Perfil - Principal";

    public PerfilPrincipalPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

}
