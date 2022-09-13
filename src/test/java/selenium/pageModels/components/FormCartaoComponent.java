package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.dataHelpers.VOs.CartaoVO;
import selenium.dataHelpers.VOs.EnderecoVO;

import java.time.Duration;

public class FormCartaoComponent {

    private WebElement campoNumCartao;
    private WebElement campoNomeCartao;
    private WebElement campoBandeira;
    private WebElement campoCodigoCartao;
    private WebElement campoDtValidade;

    private final WebElement botaoCadastro;

    private WebDriver driver;

    public FormCartaoComponent(WebDriver driver){
        this.driver = driver;

        campoNumCartao = driver.findElement(By.name("numCartao"));
        campoNomeCartao = driver.findElement(By.name("nomeCartao"));
        campoBandeira = driver.findElement(By.name("bandeira"));
        campoCodigoCartao = driver.findElement(By.name("codigoCartao"));
        campoDtValidade = driver.findElement(By.name("dtValidade"));

        botaoCadastro = driver.findElement(By.name("botaoCadastro"));
    }

    public void salvarNovoCartao(CartaoVO cartao){
        campoNumCartao.clear();
        campoNomeCartao.clear();
        campoCodigoCartao.clear();
        campoDtValidade.clear();

        campoNumCartao.sendKeys(cartao.getNumCartao());
        campoNomeCartao.sendKeys(cartao.getNomeCartao());
        campoBandeira.sendKeys(cartao.getBandeira());
        campoCodigoCartao.sendKeys(cartao.getCodigoCartao());
        campoDtValidade.sendKeys(cartao.getDtValidade());

        botaoCadastro.click();
    }

    public void editarCartao(CartaoVO cartao){
        salvarNovoCartao(cartao);
    }

}
