package selenium.scripts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import selenium.pageModels.HomePage;
import selenium.pageModels.LoginPage;
import selenium.pageModels.VOs.UsuarioVO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteLogin extends TesteAbstract{

    @Test
    public void testeLoginClienteValido(){
        driver.get("http://localhost:8080/emug/login.jsp");

        LoginPage loginPage = new LoginPage(driver);
        HomePage home = loginPage.logar(UsuarioVO.createUsuarioPadrao());

        assertTrue(home.isHomeCliente());
    }

    private static Object[][] usuariosInvalidos() {
        UsuarioVO usuario1 = UsuarioVO.createUsuarioPadrao();
        usuario1.setSenha("Senha123456");
        usuario1.setSenhaConfirmacao("Senha123456");

        UsuarioVO usuario2 = UsuarioVO.createUsuarioPadrao();
        usuario2.setEmail("emailNaoCadastrado@email.com");

        return new Object[][]{
                {usuario1},
                {usuario2}
        };
    }

    @ParameterizedTest
    @MethodSource("usuariosInvalidos")
    public void testeLoginClienteInvalido(UsuarioVO usuario){
        driver.get("http://localhost:8080/emug/login.jsp");

        LoginPage loginPage = new LoginPage(driver);

        Assertions.assertThrowsExactly(IllegalStateException.class, () -> {
            loginPage.logar(usuario);
        });

        assertEquals("Usuario/Senha incorreto.", loginPage.getMensagemAlert());
    }

}
