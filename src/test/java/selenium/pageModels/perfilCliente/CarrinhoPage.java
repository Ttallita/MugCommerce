package selenium.pageModels.perfilCliente;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pageModels.FinalizarCompraPage;
import selenium.pageModels.PageAbstract;
import selenium.utils.UtilsTeste;

import java.time.Duration;

public class CarrinhoPage extends PageAbstract{

    public static final String TITULO_PAGINA = "Carrinho";

    private static final String LINK_FINALIZAR_COMPRA = "/emug/clientes/carrinho/finalizarCompra?operacao=listarUnico";

    public CarrinhoPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public void alterarQuantidadeProduto(int quantProduto, String nomeProduto){
        String nomeBotao = "addQuant";

        if (quantProduto < 0) {
            nomeBotao = "removeQuant";
            quantProduto *= -1;
        }

        for(int i = 0; i < quantProduto; i++) {
            WebElement trProduto = getLinhaTabelaProduto(nomeProduto);
            trProduto.findElement(By.id(nomeBotao)).click();

            try {
                UtilsTeste.esperarTelaRecarregar(driver);
            } catch (TimeoutException e){
                WebElement notificacao = driver.findElement(By.className("notifications-container"));
                if (notificacao.getText() != null || !notificacao.getText().isEmpty())
                    break;
            }

        }
    }

    public void excluirProdutoCarrinho(String nomeProduto){
        WebElement botaoExcluir = this.getLinhaTabelaProduto(nomeProduto).findElement(By.cssSelector("input[value*='Excluir']"));
        botaoExcluir.click();
    }

    public int getQuantProduto(String nomeProduto){
        String quant = this.getLinhaTabelaProduto(nomeProduto)
                .findElement(By.name("quantidade"))
                .getAttribute("value");

        return Integer.parseInt(quant);
    }

    public boolean isProdutoEmCarrinho(String nomeProduto){
        try {
            this.getLinhaTabelaProduto(nomeProduto);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    private WebElement getLinhaTabelaProduto(String nomeProduto) {
        WebElement trProduto = null;
        for(WebElement e : driver.findElements(By.cssSelector("tbody > tr"))){
            if (e.findElement(By.cssSelector("td > a > h6")).getText().equals(nomeProduto)) {
                trProduto = e;
                break;
            }
        }

        if (trProduto == null)
            throw new NoSuchElementException(String.format("Produto %s não foi encontrado no carrinho!", nomeProduto));

        return trProduto;
    }

    public FinalizarCompraPage finalizarCompra(){
        try {
            UtilsTeste.getBotaoByLink(LINK_FINALIZAR_COMPRA, driver).click();
        } catch (NoSuchElementException e){
            throw new RuntimeException("Carrinho está vazio!");
        }

        return new FinalizarCompraPage(driver);
    }
}
