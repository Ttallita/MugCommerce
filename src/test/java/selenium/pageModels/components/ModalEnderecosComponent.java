package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.dataHelpers.VOs.CartaoVO;
import selenium.dataHelpers.VOs.EnderecoVO;

public class ModalEnderecosComponent extends ModalAbstract {

    public ModalEnderecosComponent(WebDriver driver){
        super(driver);
    }

    public FormEnderecoComponent adicionarNovoEndereco(){
        driver.findElement(By.id("botaoAdicionarModal")).click();
        return new FormEnderecoComponent(driver);
    }

    public FormEnderecoComponent editarEndereco(EnderecoVO endereco){
        WebElement cartao = getItemModal(endereco.getApelidoEndereco());
        cartao.findElement(By.tagName("small")).findElement(By.tagName("a")).click();
        return new FormEnderecoComponent(driver);
    }

    public void selecionarCartao(CartaoVO cartaoVO){
        WebElement cartao = getItemModal(cartaoVO.getFinalCartao());

        cartao.findElement(By.cssSelector("input[type*='checkbox']")).click();
        cartao.findElement(By.tagName("a"));
    }

    public boolean isEnderecoListado(EnderecoVO enderecoVO){
        return super.isItemListado(enderecoVO.getApelidoEndereco());
    }
}
