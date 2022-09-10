package selenium.scripts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import selenium.pageModels.CadastroClientePage;
import selenium.pageModels.HomePage;
import selenium.pageModels.LoginPage;
import selenium.pageModels.PerfilClientePage;
import selenium.pageModels.VOs.ClienteVO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteCadastroCliente extends TesteAbstract {

    @Test
    public void testeCadastroClienteValido() throws InterruptedException {
        driver.get(LINK_LOGIN);

        ClienteVO cliente = ClienteVO.createClienteVOPadrao();
        LoginPage loginPage = new LoginPage(driver);

        CadastroClientePage cadastroPage = loginPage.acessarCadastro();
        cadastroPage.cadastrar(cliente);

        assertEquals("Cadastrado com sucesso. Clique aqui para logar", cadastroPage.getMensagemAlert());
    }

    @ParameterizedTest
    @MethodSource("selenium.pageModels.dataHelpers.ClienteDataHelper#clientesInvalidos")
    public void testeCadastroClienteInvalido(ClienteVO cliente, String msgEsperada) throws InterruptedException {
        driver.get(LINK_LOGIN);
        LoginPage loginPage = new LoginPage(driver);

        CadastroClientePage cadastroPage = loginPage.acessarCadastro();
        cadastroPage.cadastrar(cliente);

        assertEquals(msgEsperada, cadastroPage.getMensagemAlert());
    }

    @Test
    public void testeCadastroNovoEndereco(){
        HomePage homeCliente = this.realizarLoginCliente();

        PerfilClientePage perfilCliente = homeCliente.acessarPerfilCliente();

//        perfilCliente.
    }

}
