package selenium.pageModels.dashboard;

import org.openqa.selenium.WebDriver;
import selenium.pageModels.PageAbstract;
import selenium.pageModels.formularios.FormProdutoPage;
import selenium.utils.UtilsTeste;

public class ProdutoAdmPage extends PageAbstract {

    public static final String TITULO_PAGINA = "Gerenciar - Produtos";

    public static final String LINK_ADICIONAR_PRODUTO = "/emug/gerenciar/formularios/formProduto.jsp";

    public ProdutoAdmPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public FormProdutoPage adicionarProduto() {
        UtilsTeste.getBotaoByLink(LINK_ADICIONAR_PRODUTO, driver).click();
        return new FormProdutoPage(driver);
    }

}
