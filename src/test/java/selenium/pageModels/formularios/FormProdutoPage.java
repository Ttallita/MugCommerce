package selenium.pageModels.formularios;

import org.openqa.selenium.WebDriver;
import selenium.dataHelpers.VOs.ProdutoVO;
import selenium.pageModels.PageAbstract;
import selenium.pageModels.components.FormProdutoComponent;

public class FormProdutoPage extends PageAbstract {

    private final FormProdutoComponent formProduto;
    public static final String TITULO_PAGINA = "Cadastro";

    public FormProdutoPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
        formProduto = new FormProdutoComponent(driver);
    }

    public void salvarNovoProduto(ProdutoVO produtoVO) {
        formProduto.salvarNovoProduto(produtoVO);
    }



}
