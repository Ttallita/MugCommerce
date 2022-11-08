package selenium.scripts.unitario.cliente;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import selenium.pageModels.HomePage;
import selenium.pageModels.LoginPage;
import selenium.dataHelpers.VOs.UsuarioVO;
import selenium.pageModels.components.HeaderAdmComponent;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.scripts.unitario.TesteAbstract;
import selenium.utils.UtilsTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteLogin extends TesteAbstract {

    @Override
    protected void configurarCenarioTeste() { }

    @Test
    public void testeLoginClienteValido(){
        driver.get(LINK_LOGIN);

        LoginPage loginPage = new LoginPage(driver);
        HomePage home = loginPage.logar(UsuarioVO.createUsuarioClientePadrao());

        assertTrue(home.getHeader(driver) instanceof HeaderClienteComponent);
    }

    @Test
    public void testeLoginAdmValido(){
        driver.get(LINK_LOGIN);

        LoginPage loginPage = new LoginPage(driver);
        HomePage home = loginPage.logar(UsuarioVO.createUsuarioAdmPadrao());

        assertTrue(home.getHeader(driver) instanceof HeaderAdmComponent);
    }

    @ParameterizedTest
    @MethodSource("selenium.dataHelpers.UsuarioDataHelper#usuariosInvalidos")
    public void testeLoginClienteInvalido(UsuarioVO usuario){
        driver.get(LINK_LOGIN);

        LoginPage loginPage = new LoginPage(driver);

        Assertions.assertThrowsExactly(IllegalStateException.class, () -> loginPage.logar(usuario));

        assertEquals("Usuario/Senha incorreto.", UtilsTeste.getMensagemAlert(driver));
    }

}
