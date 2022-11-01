package selenium.scripts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import selenium.dataHelpers.VOs.ProdutoVO;
import selenium.pageModels.DashboardPage;
import selenium.pageModels.HomePage;
import selenium.pageModels.components.HeaderAdmComponent;
import selenium.pageModels.dashboard.ProdutoAdmPage;
import selenium.pageModels.formularios.FormProdutoPage;

public class TesteProduto extends TesteAbstract{

    private HeaderAdmComponent header;

    @Override
    void configurarCenarioTeste() {
        HomePage homePage = realizarLoginAdmPadrao();
        header = (HeaderAdmComponent) homePage.getHeader(driver);
    }

    @Test
    public void testaCadastroProduto() {
        DashboardPage dashboardPage = header.acessarDashboard();
        ProdutoAdmPage produtoAdmPage = dashboardPage.acessarProdutos();

        FormProdutoPage formProdutoPage = produtoAdmPage.adicionarProduto();

        formProdutoPage.salvarNovoProduto(ProdutoVO.criaProdutoTeste());

        Assertions.assertEquals("Gerenciar - Produtos", driver.getTitle());
    }

    @Test
    public void testaEditarProduto() {

    }


}
