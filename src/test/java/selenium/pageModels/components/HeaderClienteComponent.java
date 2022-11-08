package selenium.pageModels.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.pageModels.HomePage;
import selenium.pageModels.perfilCliente.CarrinhoPage;
import selenium.pageModels.perfilCliente.PerfilClientePage;
import selenium.utils.UtilsTeste;

import java.util.HashMap;
import java.util.Map;

public class HeaderClienteComponent extends HeaderComponentAbstract {

    private static final String LINK_PERFIL_CLIENTE = "/emug/clientes?operacao=listar";
    private static final String LINK_CARRINHO = "/emug/cliente/carrinho.jsp";
    private static final String LINK_LOGOUT = "/emug/logout";

    public HeaderClienteComponent(WebDriver driver) {
        super(driver);
    }

    public PerfilClientePage acessarPagina(String pagina) {
        UtilsTeste.getBotaoByLink(LINK_PERFIL_CLIENTE, driver).click();

        return new PerfilClientePage(driver);
    }

    public PerfilClientePage acessarPerfil(){
        return this.acessarPagina("perfil");
    }

    public CarrinhoPage acessarCarrinho() {
        UtilsTeste.getBotaoByLink(LINK_CARRINHO, driver).click();
        return new CarrinhoPage(driver);
    }

    public HomePage deslogar() {
        UtilsTeste.getBotaoByLink(LINK_LOGOUT, driver).click();
        return new HomePage(driver);
    }

}
