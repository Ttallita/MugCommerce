package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderClienteComponent extends HeaderComponentAbstract {

    private final WebElement botaoPerfilCliente;

    private static final String LINK_PERFIL_CLIENTE = "/emug/clientes?operacao=listar";

    public HeaderClienteComponent(WebDriver driver) {
        super(driver);

        botaoPerfilCliente = driver.findElement(By.cssSelector("a[href*='" + LINK_PERFIL_CLIENTE+ "']"));
    }

    public void acessarPerfil(){
        botaoPerfilCliente.click();
    }

}
