package selenium.pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import selenium.pageModels.perfilCliente.CarrinhoPage;
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
        try {
            UtilsTeste.getBotaoByValueInput("Adicionar ao carrinho", driver).click();
        } catch (NoSuchElementException e){
            if (driver.findElement(By.cssSelector("form[action='/emug/clientes/carrinho'] > div")).getText().equals("Sem produtos no estoque"))
                return null;

            throw e;
        }
        return new CarrinhoPage(driver);
    }

}
