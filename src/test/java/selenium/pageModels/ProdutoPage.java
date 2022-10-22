package selenium.pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utils.UtilsTeste;

public class ProdutoPage extends PageAbstract{

    public ProdutoPage(WebDriver driver, String tituloPagina) {
        super(driver, tituloPagina);
    }

    public static ProdutoPage abrirPaginaProduto(String nomeProduto){
        driver.findElement(By.linkText(nomeProduto)).click();
        return new ProdutoPage(driver, nomeProduto);
    }

    public CarrinhoPage adicionarProdutoCarrinho(){
        UtilsTeste.getBotaoByValueInput("Adicionar ao carrinho", driver).click();
        return new CarrinhoPage(driver);
    }

}
