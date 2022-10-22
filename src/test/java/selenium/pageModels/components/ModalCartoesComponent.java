package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.dataHelpers.VOs.CartaoVO;
import selenium.dataHelpers.VOs.EnderecoVO;

import java.util.List;
import java.util.NoSuchElementException;

public class ModalCartoesComponent extends ModalAbstract{

    public ModalCartoesComponent(WebDriver driver){
        super(driver);
    }

    public FormCartaoComponent adicionarNovoCartao(){
        driver.findElement(By.id("botaoAdicionarModal")).click();
        return new FormCartaoComponent(driver);
    }

    public FormCartaoComponent editarCartao(CartaoVO cartaoVO){
        WebElement cartao = getItemModal(cartaoVO.getFinalCartao());
        cartao.findElement(By.tagName("small")).findElement(By.tagName("a")).click();
        return new FormCartaoComponent(driver);
    }

    public void selecionarCartao(CartaoVO cartaoVO){
        WebElement cartao = getItemModal(cartaoVO.getFinalCartao());

        cartao.findElement(By.cssSelector("input[type*='checkbox']")).click();
        cartao.findElement(By.tagName("a"));
    }

    public boolean isCartaoListado(CartaoVO cartao){
        return super.isItemListado(cartao.getFinalCartao());
    }

}
