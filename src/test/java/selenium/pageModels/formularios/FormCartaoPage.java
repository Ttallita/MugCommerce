package selenium.pageModels.formularios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.dataHelpers.VOs.CartaoVO;
import selenium.pageModels.PageAbstract;
import selenium.pageModels.components.FormCartaoComponent;

public class FormCartaoPage extends PageAbstract{

    private final FormCartaoComponent formCartao;
    private final WebElement botaoCadastro;

    public static final String TITULO_PAGINA = "Cadastro cartão";

    public FormCartaoPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);

        formCartao = new FormCartaoComponent(driver);
        botaoCadastro = driver.findElement(By.name("botaoCadastro"));
    }

    public FormCartaoPage salvarNovoCartao(CartaoVO cartao){
        formCartao.salvarNovoCartao(cartao);
        return this;
    }

    public FormCartaoPage editarCartao(CartaoVO cartao){
        formCartao.editarCartao(cartao);
        return this;
    }

}
