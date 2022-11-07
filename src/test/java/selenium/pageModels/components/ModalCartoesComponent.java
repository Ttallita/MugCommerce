package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.dataHelpers.VOs.CartaoVO;

import java.util.List;

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
        cartao.findElement(By.tagName("a")).click();
        return new FormCartaoComponent(driver);
    }

    public void selecionarCartao(CartaoVO cartaoVO){
        WebElement cartao = getItemModal(cartaoVO.getFinalCartao());
        WebElement checkBox = cartao.findElement(By.cssSelector("input[type*='checkbox']"));

        if (!checkBox.isSelected())
            checkBox.click();

        cartao.findElement(By.tagName("a"));
    }

    public void alterarCartoes(List<CartaoVO> cartoes){
        for(CartaoVO cartao : cartoes){
            this.selecionarCartao(cartao);
        }

        super.alterarItensSelecionados();
    }

    public boolean isCartaoListado(CartaoVO cartao){
        return super.isItemListado(cartao.getFinalCartao());
    }

}
