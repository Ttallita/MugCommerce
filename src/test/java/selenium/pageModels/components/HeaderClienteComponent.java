package selenium.pageModels.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.pageModels.perfilCliente.CarrinhoPage;
import selenium.pageModels.perfilCliente.PerfilClientePage;
import selenium.utils.UtilsTeste;

import java.util.HashMap;
import java.util.Map;

public class HeaderClienteComponent extends HeaderComponentAbstract {

    private final Map<String, Runnable> mapPaginaBotao;

    private static final String LINK_PERFIL_CLIENTE = "/emug/clientes?operacao=listar";
    private static final String LINK_CARRINHO = "/emug/cliente/carrinho.jsp";

    public HeaderClienteComponent(WebDriver driver) {
        super(driver);

        WebElement botaoPerfilCliente = UtilsTeste.getBotaoByLink(LINK_PERFIL_CLIENTE, driver);

        mapPaginaBotao = new HashMap<>();
        mapPaginaBotao.put("perfil", botaoPerfilCliente::click);
    }

    public PerfilClientePage acessarPagina(String pagina) {
        mapPaginaBotao.get(pagina).run();

        return new PerfilClientePage(driver);
    }

    public CarrinhoPage acessarCarrinho() {
        UtilsTeste.getBotaoByLink(LINK_CARRINHO, driver).click();
        return new CarrinhoPage(driver);
    }

}
