package selenium.scripts;

import org.junit.jupiter.api.Test;
import selenium.pageModels.ClienteVO;
import selenium.pageModels.LoginPage;


public class TesteLogin extends TesteAbstract{

    @Test
    public void testeLoginCliente(){
        ClienteVO cliente = ClienteVO.createClienteVOPadrao();

        driver.get("http://localhost:8080/emug/login.jsp");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.logar(cliente);
    }

}
