package selenium.scripts;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.pageModels.CadastroClientePage;
import selenium.pageModels.ClienteVO;
import selenium.pageModels.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteCadastroCliente {

    private WebDriver driver;

    @BeforeEach
    public void setup(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testeCadastroClienteValido() throws InterruptedException {
        ClienteVO cliente = ClienteVO.createClienteVOPadrao();

        driver.get("http://localhost:8080/emug/login.jsp");
        LoginPage loginPage = new LoginPage(driver);

        CadastroClientePage cadastroPage = loginPage.acessarCadastro();
        cadastroPage.cadastrar(cliente);

        assertEquals("Cadastrado com sucesso. Clique aqui para logar", cadastroPage.getMensagemAlert());
    }

    @Test
    public void testeCadastroClienteInvalido() throws InterruptedException {
        ClienteVO cliente = ClienteVO.createClienteVOPadrao();
        cliente.setSenha("senhaInvalida");

        driver.get("http://localhost:8080/emug/login.jsp");
        LoginPage loginPage = new LoginPage(driver);

        CadastroClientePage cadastroPage = loginPage.acessarCadastro();
        cadastroPage.cadastrar(cliente);

        assertEquals("As senhas não coincidem", cadastroPage.getMensagemAlert());
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}
