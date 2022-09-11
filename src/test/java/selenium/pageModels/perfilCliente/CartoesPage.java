package selenium.pageModels.perfilCliente;

import org.openqa.selenium.WebDriver;
import selenium.pageModels.formularios.FormCartaoPage;
import selenium.utils.UtilsTeste;

public class CartoesPage extends PerfilPage {

    public static final String TITULO_PAGINA = "Perfil - Cartões";

    public static final String LINK_ADICIONAR_CARTAO = "/emug/formularios/formCartaoCredito.jsp";

    public CartoesPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public FormCartaoPage adicionarCartao(){
        UtilsTeste.getBotaoByLink(LINK_ADICIONAR_CARTAO, driver).click();

        return new FormCartaoPage(driver);
    }
}
