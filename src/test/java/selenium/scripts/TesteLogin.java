package selenium.scripts;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import selenium.pageModels.CadastroClientePage;
import selenium.pageModels.ClienteVO;
import selenium.pageModels.LoginPage;

public class TesteLogin {

    private static WebDriver driver;

    @BeforeAll
    public static void setup(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @Test
    public void testeLoginCliente(){
        driver.get("http://localhost:8080/emug/login.html");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.logar("a", "a","a");
    }

    @Test
    public void testeCadastroCliente(){
        ClienteVO cliente = new ClienteVO("email@email.com", "senha123456");

        driver.get("http://localhost:8080/emug/login.html");
        LoginPage loginPage = new LoginPage(driver);

        CadastroClientePage cadastroPage = loginPage.acessarCadastro();
        cadastroPage.cadastrar(cliente);
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }

}
