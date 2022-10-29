package selenium.pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.dataHelpers.VOs.CartaoVO;
import selenium.dataHelpers.VOs.EnderecoVO;
import selenium.pageModels.components.ModalCartoesComponent;
import selenium.pageModels.components.ModalEnderecosComponent;

import java.util.List;

public class FinalizarCompraPage extends PageAbstract{

    public static final String TITULO_PAGINA = "Finalizar compra";

    public FinalizarCompraPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public ModalEnderecosComponent abrirModalAlterarEnderecoEntrega(){
        driver.findElement(By.id("alterarEndereco")).click();
        return new ModalEnderecosComponent(driver);
    }

    public ModalEnderecosComponent abrirModalAlterarEnderecoCobranca(){
        try {
            driver.findElement(By.id("alterarEnderecoCobranca")).click();
        } catch (ElementNotInteractableException e){
            driver.findElement(By.id("alterarEndereco")).click();
        }
        return new ModalEnderecosComponent(driver);
    }

    public ModalCartoesComponent abrirModalAlterarFormaPagamento(){
        driver.findElement(By.id("alterarPagamento")).click();
        return new ModalCartoesComponent(driver);
    }

    public boolean isCartaoListado(CartaoVO cartao){
        List<WebElement> cartoes = driver.findElement(By.id("divCartoes")).findElements(By.tagName("li"));
        return isElementoListado(cartoes, cartao.getFinalCartao());
    }

    public boolean isEnderecoEntregaListado(EnderecoVO endereco){

        List<WebElement> enderecos = driver.findElement(By.id("divEnderecoEntrega")).findElements(By.tagName("li"));
        return isElementoListado(enderecos, endereco.getApelidoEndereco());
    }

    public boolean isEnderecoCobrancaListado(EnderecoVO endereco){
        List<WebElement> enderecos = driver.findElement(By.id("divEnderecoCobranca")).findElements(By.tagName("li"));
        return isElementoListado(enderecos, endereco.getApelidoEndereco());
    }

    private static boolean isElementoListado(List<WebElement> elementos, String identificadorElemento) {
        for (WebElement e: elementos){
            if (e.getText().contains(identificadorElemento))
                return true;
        }
        return false;
    }

}
