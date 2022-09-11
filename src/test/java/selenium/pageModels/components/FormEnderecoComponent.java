package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.dataHelpers.VOs.EnderecoVO;

import java.time.Duration;

public class FormEnderecoComponent {

    private final WebElement campoTpResidencia;
    private final WebElement campoTpLogradouro;
    private final WebElement campoLogradouro;
    private final WebElement campoBairro;
    private final WebElement campoNumeroEndereco;
    private final WebElement campoCep;
    private final WebElement campoEstado;
    private final WebElement campoCidade;
    private final WebElement campoApelidoEndereco;
    private final WebElement campoObservacaoEndereco;

    private final WebElement botaoCadastro;

    private WebDriver driver;

    public FormEnderecoComponent(WebDriver driver){
        campoTpResidencia = driver.findElement(By.name("tpResidencia"));
        campoTpLogradouro = driver.findElement(By.name("tpLogradouro"));
        campoLogradouro = driver.findElement(By.name("logradouro"));
        campoBairro = driver.findElement(By.name("bairro"));
        campoNumeroEndereco = driver.findElement(By.name("numeroEndereco"));
        campoCep = driver.findElement(By.name("cep"));
        campoEstado = driver.findElement(By.name("estado"));
        campoCidade = driver.findElement(By.name("cidade"));
        campoApelidoEndereco = driver.findElement(By.name("apelidoEndereco"));
        campoObservacaoEndereco = driver.findElement(By.name("observacaoEndereco"));

        botaoCadastro = driver.findElement(By.name("botaoCadastro"));

        this.driver = driver;
    }

    public void salvarNovoEndereco(EnderecoVO endereco){
        campoTpResidencia.sendKeys(endereco.getTpResidencia());
        campoTpLogradouro.sendKeys(endereco.getTpLogradouro());
        campoLogradouro.sendKeys(endereco.getLogradouro());
        campoBairro.sendKeys(endereco.getBairro());
        campoNumeroEndereco.sendKeys(endereco.getNumeroEndereco());
        campoCep.sendKeys(endereco.getCep());
        campoEstado.sendKeys(endereco.getEstado());

        if(!endereco.getEstado().isBlank()) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cidade"), "Mogi das Cruzes"));
            campoCidade.sendKeys(endereco.getCidade());
        }

        campoApelidoEndereco.sendKeys(endereco.getApelidoEndereco());
        campoObservacaoEndereco.sendKeys(endereco.getObservacaoEndereco());

    }
}
