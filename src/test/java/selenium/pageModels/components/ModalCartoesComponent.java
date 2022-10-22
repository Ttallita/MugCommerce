package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.dataHelpers.VOs.CartaoVO;

import java.util.List;
import java.util.NoSuchElementException;

public class ModalCartoesComponent {

    private WebDriver driver;
    public ModalCartoesComponent(WebDriver driver){
        this.driver = driver;
    }

    public FormCartaoComponent adicionarNovoCartao(){
        driver.findElement(By.id("botaoAdicionarModal")).click();
        return new FormCartaoComponent(driver);
    }

    public void selecionarCartao(CartaoVO cartaoVO){
        WebElement cartao = getItemModal(cartaoVO.getFinalCartao());

        cartao.findElement(By.cssSelector("input[type*='checkbox']")).click();
        cartao.findElement(By.tagName("a"));
    }

    public void alterarCartoesSelecionados(){
        driver.findElement(By.id("botaoAlterarModal")).click();
    }

    public boolean isCartaoListado(CartaoVO cartao){
        try {
            getItemModal(cartao.getFinalCartao());
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    private WebElement getItemModal(String nomeItem) {
        for(WebElement i : driver.findElements(By.className("form-check"))){
            if (i.findElement(By.tagName("label")).getText().contains(nomeItem))
                return i;
        }

        throw new NoSuchElementException("Item não encontrado no modal");
    }
}
