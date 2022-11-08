package selenium.scripts.unitario.cliente;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.dataHelpers.ProdutoDataHelper;
import selenium.pageModels.HomePage;
import selenium.pageModels.ProdutoPage;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.pageModels.perfilCliente.CarrinhoPage;
import selenium.scripts.TesteAbstract;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TesteCarrinho extends TesteAbstract {

    private HomePage homeCliente;
    private HeaderClienteComponent headerCliente;

    @Override
    protected void configurarCenarioTeste() {
        this.homeCliente = this.realizarLoginClientePadrao();
        headerCliente = (HeaderClienteComponent) homeCliente.getHeader(driver);
    }

    @Test
    public void testeAdicionarProdutoPesquisaCarrinho(){
        String nomeProduto = ProdutoDataHelper.getNomeProdutoAleatorio();

        homeCliente.pesquisar(nomeProduto);
        adicionaProdutoAoCarrinho(driver, headerCliente, nomeProduto);
    }

    @ParameterizedTest
    @MethodSource("selenium.dataHelpers.ProdutoDataHelper#quantProdutosCarrinho")
    public void testeAlterarQuantidade(int quantAlterar, String nomeProduto) {
        adicionaProdutoAoCarrinho(driver, headerCliente, nomeProduto);

        CarrinhoPage carrinho = headerCliente.acessarCarrinho();

        if(quantAlterar < 0) // Garante que o carrinho sempre tenha uma quantidade válida de produtos para o teste
            carrinho.alterarQuantidadeProduto(-quantAlterar, nomeProduto);

        int quantAnterior = carrinho.getQuantProduto(nomeProduto);
        carrinho.alterarQuantidadeProduto(quantAlterar, nomeProduto);

        assertEquals(quantAnterior + quantAlterar, carrinho.getQuantProduto(nomeProduto));
    }

    @Test
    public void testeExcluirProdutocarrinho(){
        String nomeProduto = ProdutoDataHelper.getNomeProdutoAleatorio();

        adicionaProdutoAoCarrinho(driver, headerCliente, nomeProduto);

        CarrinhoPage carrinho = headerCliente.acessarCarrinho();
        carrinho.excluirProdutoCarrinho(nomeProduto);

        assertFalse(carrinho.isProdutoEmCarrinho(nomeProduto));
    }

    public static CarrinhoPage adicionaProdutoAoCarrinho(WebDriver driver, HeaderClienteComponent headerCliente, String nomeProduto) {
        headerCliente.pesquisar(nomeProduto);
        ProdutoPage produtoPage = ProdutoPage.abrirPaginaProduto(nomeProduto);

//        Thread.sleep(1000L);
        CarrinhoPage carrinho = produtoPage.adicionarProdutoCarrinho();

        if (carrinho != null)
            // Verifica se produto foi adicionado ao carinho
            driver.findElement(By.linkText(nomeProduto));

        return carrinho;
    }

}
