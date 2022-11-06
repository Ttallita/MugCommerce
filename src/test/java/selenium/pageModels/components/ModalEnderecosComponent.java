package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.dataHelpers.VOs.EnderecoVO;

public class ModalEnderecosComponent extends ModalAbstract {

    public ModalEnderecosComponent(WebDriver driver){
        super(driver);
    }

    public FormEnderecoComponent adicionarNovoEndereco(){
        driver.findElement(By.id("botaoAdicionarModal")).click();
        return new FormEnderecoComponent(driver);
    }

    public FormEnderecoComponent editarEndereco(EnderecoVO enderecoVO){
        WebElement endereco = getItemModal(enderecoVO.getApelidoEndereco());
        endereco.findElement(By.tagName("a")).click();
        return new FormEnderecoComponent(driver);
    }

    public void alterarEndereco(EnderecoVO enderecoVO){
        WebElement cartao = getItemModal(enderecoVO.getApelidoEndereco());

        cartao.findElement(By.cssSelector("input[type*='radio']")).click();
        cartao.findElement(By.tagName("a"));

        super.alterarItensSelecionados();
    }

    public boolean isEnderecoListado(EnderecoVO enderecoVO){
        return super.isItemListado(enderecoVO.getApelidoEndereco());
    }
}
