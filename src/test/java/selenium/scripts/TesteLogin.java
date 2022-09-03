package selenium.scripts;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.pageModels.ClienteVO;
import selenium.pageModels.LoginPage;


public class TesteLogin {

    private WebDriver driver;

    @BeforeEach
    public void setup(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testeLoginCliente(){
        ClienteVO cliente = ClienteVO.createClienteVOPadrao();

        driver.get("http://localhost:8080/emug/login.jsp");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.logar(cliente);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}
