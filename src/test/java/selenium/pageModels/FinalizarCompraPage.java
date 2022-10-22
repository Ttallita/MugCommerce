package selenium.pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.dataHelpers.VOs.CartaoVO;
import selenium.pageModels.components.ModalCartoesComponent;

import java.util.List;

public class FinalizarCompraPage extends PageAbstract{

    public static final String TITULO_PAGINA = "Finalizar compra";

    public FinalizarCompraPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public void abrirModalAlterarEnderecoEntrega(){
        driver.findElement(By.id("alterarEndereco")).click();
    }

    public void abrirModalAlterarEnderecoCobranca(){
        driver.findElement(By.id("alterarEnderecoCobranca")).click();
    }

    public ModalCartoesComponent abrirModalAlterarFormaPagamento(){
        driver.findElement(By.id("alterarPagamento")).click();
        return new ModalCartoesComponent(driver);
    }

    public boolean isCartaoListado(CartaoVO cartao){
        List<WebElement> cartoes = driver.findElement(By.id("divCartoes")).findElements(By.tagName("li"));

        for (WebElement c: cartoes){
            if (c.getText().contains(cartao.getFinalCartao()))
                return true;
        }
        return false;
    }

}
