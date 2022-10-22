package selenium.scripts;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import selenium.pageModels.HomePage;
import selenium.pageModels.ProdutoPage;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.pageModels.perfilCliente.CarrinhoPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TesteCarrinho extends TesteAbstract{

    private HomePage homeCliente;
    private HeaderClienteComponent headerCliente;

    @Override
    void configurarCenarioTeste() {
        this.homeCliente = this.realizarLoginClientePadrao();
        headerCliente = (HeaderClienteComponent) homeCliente.getHeader(driver);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Caneca ursinho"})
    public void testeAdicionarProdutoIndexCarrinho(String nomeProduto) {
        homeCliente.abrirPaginaProduto(nomeProduto).adicionarProdutoCarrinho();

        // Verifica se produto foi adicionado ao carinho
        driver.findElement(By.linkText(nomeProduto));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Caneca ursinho"})
    public void testeAdicionarProdutoPesquisaCarrinho(String nomeProduto){
        homeCliente.pesquisar(nomeProduto);

        ProdutoPage.abrirPaginaProduto(nomeProduto).adicionarProdutoCarrinho();

        // Verifica se produto foi adicionado ao carinho
        driver.findElement(By.linkText(nomeProduto));
    }

    @ParameterizedTest
    @MethodSource("selenium.dataHelpers.ProdutoDataHelper#quantProdutosCarrinho")
    public void testeAlterarQuantidade(int quantAlterar, String nomeProduto) {
        this.testeAdicionarProdutoIndexCarrinho(nomeProduto);
        CarrinhoPage carrinho = headerCliente.acessarCarrinho();

        if(quantAlterar < 0) // Garante que o carrinho sempre tenha uma quantidade válida de produtos para o teste
            carrinho.alterarQuantidadeProduto(-quantAlterar, nomeProduto);

        int quantAnterior = carrinho.getQuantProduto(nomeProduto);
        carrinho.alterarQuantidadeProduto(quantAlterar, nomeProduto);

        assertEquals(quantAnterior + quantAlterar, carrinho.getQuantProduto(nomeProduto));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Caneca ursinho"})
    public void testeExcluirProdutocarrinho(String nomeProduto){
        this.testeAdicionarProdutoIndexCarrinho(nomeProduto);
        CarrinhoPage carrinho = headerCliente.acessarCarrinho();

        carrinho.excluirProdutoCarrinho(nomeProduto);

        assertFalse(carrinho.isProdutoEmCarrinho(nomeProduto));
    }

}
