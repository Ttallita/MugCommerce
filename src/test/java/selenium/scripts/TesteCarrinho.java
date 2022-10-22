package selenium.scripts;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.pageModels.CarrinhoPage;
import selenium.pageModels.HomePage;
import selenium.pageModels.ProdutoPage;
import selenium.pageModels.components.HeaderClienteComponent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TesteCarrinho extends TesteAbstract{

    HomePage homeCliente;

    @BeforeEach
    public void setup(){
        super.setup();
        this.homeCliente = this.realizarLoginClientePadrao();
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

        // Verifica se produto pesquisado foi encontrado
        ProdutoPage.abrirPaginaProduto(nomeProduto);
    }

    @ParameterizedTest
    @MethodSource("selenium.dataHelpers.ProdutoDataHelper#quantProdutosCarrinho")
    public void testeAlterarQuantidade(int quantAlterar, String nomeProduto) {
        this.testeAdicionarProdutoIndexCarrinho(nomeProduto);

        HeaderClienteComponent headerCliente = (HeaderClienteComponent) homeCliente.getHeader(driver);
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

        HeaderClienteComponent headerCliente = (HeaderClienteComponent) homeCliente.getHeader(driver);
        CarrinhoPage carrinho = headerCliente.acessarCarrinho();

        carrinho.excluirProdutoCarrinho(nomeProduto);

        assertFalse(carrinho.isProdutoEmCarrinho(nomeProduto));
    }

}
