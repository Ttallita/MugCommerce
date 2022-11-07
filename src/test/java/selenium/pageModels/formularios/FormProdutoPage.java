package selenium.pageModels.formularios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.dataHelpers.VOs.ProdutoVO;
import selenium.pageModels.PageAbstract;
import selenium.pageModels.components.FormProdutoComponent;

import java.util.List;

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


    public void inativaProduto(ProdutoVO produtoVO) {
        List<WebElement> botoesPagination = driver.findElements(By.className("page-link"));
        botoesPagination.get(2).click();

        WebElement trProduto = driver.findElements(By.tagName("tr"))
                .stream()
                .filter(element -> {
                    String textoProduto = element.getText();

                    return textoProduto.contains(produtoVO.getNome()) && textoProduto.contains("2,05");
                })
                .findFirst()
                .orElse(null);

        if(trProduto == null)
            throw new RuntimeException("Erro ao encontrar o produto");

        WebElement botaoInativarProduto = trProduto.findElement(By.name("botaoModalDeletar"));
        botaoInativarProduto.click();

        WebElement categoriaStatus = driver.findElement(By.id("categoriaStatus"));
        categoriaStatus.sendKeys("Fora do mercado");

        WebElement justificativa = driver.findElement(By.id("justificativa"));
        justificativa.sendKeys("Testeeeeeeeeee");

        WebElement botaoEnviaJustificativa = driver.findElement(By.id("botaoInativarProduto"));
        botaoEnviaJustificativa.click();
    }
}
