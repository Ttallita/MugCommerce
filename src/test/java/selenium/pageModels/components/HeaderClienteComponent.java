package selenium.pageModels.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utils.UtilsTeste;

public class HeaderClienteComponent extends HeaderComponentAbstract {

    private final WebElement botaoPerfilCliente;

    private static final String LINK_PERFIL_CLIENTE = "/emug/clientes?operacao=listar";

    public HeaderClienteComponent(WebDriver driver) {
        super(driver);

        botaoPerfilCliente = UtilsTeste.getBotaoByLink(LINK_PERFIL_CLIENTE, driver);
    }

    public void acessarPerfil(){
        botaoPerfilCliente.click();
    }

}
