package selenium.scripts.unitario.adm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import selenium.dataHelpers.VOs.ProdutoVO;
import selenium.pageModels.DashboardPage;
import selenium.pageModels.HomePage;
import selenium.pageModels.components.HeaderAdmComponent;
import selenium.pageModels.dashboard.ProdutoAdmPage;
import selenium.pageModels.formularios.FormProdutoPage;
import selenium.scripts.TesteAbstract;
import selenium.utils.UtilsTeste;

public class TesteCadastroProduto extends TesteAbstract {

    private HeaderAdmComponent header;

    @Override
    protected void configurarCenarioTeste() {
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

    @ParameterizedTest
    @MethodSource("selenium.dataHelpers.ProdutoDataHelper#produtosInvalidos")
    public void testeCadastroProdutosInvalidos(ProdutoVO produtoVO, String msgEsperada) {
        DashboardPage dashboardPage = header.acessarDashboard();
        ProdutoAdmPage produtoAdmPage = dashboardPage.acessarProdutos();

        FormProdutoPage formProdutoPage = produtoAdmPage.adicionarProduto();

        formProdutoPage.salvarNovoProduto(produtoVO);

        Assertions.assertEquals(msgEsperada, UtilsTeste.getMensagemAlert(driver));
    }

    @Test
    public void testaInativaProduto() {
        ProdutoVO produtoVO = ProdutoVO.criaProdutoTeste();

        DashboardPage dashboardPage = header.acessarDashboard();
        ProdutoAdmPage produtoAdmPage = dashboardPage.acessarProdutos();

        FormProdutoPage formProdutoPage = produtoAdmPage.adicionarProduto();

        formProdutoPage.salvarNovoProduto(produtoVO);
        formProdutoPage.inativaProduto(produtoVO);

        Assertions.assertEquals("Gerenciar - Produtos", driver.getTitle());
    }
}
