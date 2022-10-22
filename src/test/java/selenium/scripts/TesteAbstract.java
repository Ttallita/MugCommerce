package selenium.scripts;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.dataHelpers.VOs.UsuarioVO;
import selenium.pageModels.HomePage;
import selenium.pageModels.LoginPage;

import java.time.Duration;

public abstract class TesteAbstract {

    protected WebDriver driver;

    protected static final String LINK_LOGIN = "http://localhost:8080/emug/login.jsp";

    @BeforeEach
    public void setup(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    public HomePage realizarLoginClientePadrao(){
        driver.get(LINK_LOGIN);
        LoginPage loginPage = new LoginPage(driver);

        return loginPage.logar(UsuarioVO.createUsuarioClientePadrao());
    }

}
