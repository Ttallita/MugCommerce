package selenium.scripts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import selenium.pageModels.HomePage;
import selenium.pageModels.LoginPage;
import selenium.dataHelpers.VOs.UsuarioVO;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.utils.UtilsTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteLogin extends TesteAbstract{

    /*
        Lembrar de inserir o cliente padrão de login do arquivo emug.sql
     */
    @Test
    public void testeLoginClienteValido(){
        driver.get(LINK_LOGIN);

        LoginPage loginPage = new LoginPage(driver);
        HomePage home = loginPage.logar(UsuarioVO.createUsuarioLoginPadrao());

        assertTrue(home.getHeader() instanceof HeaderClienteComponent);
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
