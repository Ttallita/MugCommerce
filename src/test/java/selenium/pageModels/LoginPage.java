package selenium.pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageAbstract{

    private final WebElement campoEmail;
    private final WebElement campoSenha;
    private final WebElement campoSenhaConfirmacao;

    private final WebElement botaoCadastro;
    private final WebElement botaoLogin;

    public static final String TITULO_PAGINA = "Login";

    public LoginPage(WebDriver driver){
        super(driver, TITULO_PAGINA);

        campoEmail = driver.findElement(By.name("email"));
        campoSenha = driver.findElement(By.name("senha"));
        campoSenhaConfirmacao = driver.findElement(By.name("senhaConfirmacao"));

        botaoCadastro = driver.findElement(By.name("botaoCadastro"));
        botaoLogin = driver.findElement(By.name("botaoLogin"));
    }

    public HomePage logar(ClienteVO cliente){
        campoEmail.sendKeys(cliente.getEmail());
        campoSenha.sendKeys(cliente.getSenha());
        campoSenhaConfirmacao.sendKeys(cliente.getSenhaConfirmacao());

        botaoLogin.click();

        return new HomePage(driver);
    }

    public CadastroClientePage acessarCadastro(){
        botaoCadastro.click();
        return new CadastroClientePage(driver);
    }

}
