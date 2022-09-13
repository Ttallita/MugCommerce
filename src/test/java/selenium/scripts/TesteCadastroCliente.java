package selenium.scripts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import selenium.dataHelpers.VOs.CartaoVO;
import selenium.dataHelpers.VOs.EnderecoVO;
import selenium.pageModels.CadastroClientePage;
import selenium.pageModels.HomePage;
import selenium.pageModels.LoginPage;
import selenium.pageModels.formularios.FormCartaoPage;
import selenium.pageModels.formularios.FormEnderecoPage;
import selenium.pageModels.perfilCliente.CartoesPage;
import selenium.pageModels.perfilCliente.EnderecosPage;
import selenium.pageModels.perfilCliente.PerfilPrincipalPage;
import selenium.dataHelpers.VOs.ClienteVO;
import selenium.utils.UtilsTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteCadastroCliente extends TesteAbstract {

    @Test
    public void testeCadastroClienteValido() {
        driver.get(LINK_LOGIN);

        ClienteVO cliente = ClienteVO.createClienteVOPadrao();
        LoginPage loginPage = new LoginPage(driver);

        CadastroClientePage cadastroPage = loginPage.acessarCadastro();
        cadastroPage.cadastrar(cliente);

        assertEquals("Cadastrado com sucesso. Clique aqui para logar", cadastroPage.getMensagemAlert());
    }

    @ParameterizedTest
    @MethodSource("selenium.dataHelpers.ClienteDataHelper#clientesInvalidos")
    public void testeCadastroClienteInvalido(ClienteVO cliente, String msgEsperada) {
        driver.get(LINK_LOGIN);
        LoginPage loginPage = new LoginPage(driver);

        CadastroClientePage cadastroPage = loginPage.acessarCadastro();
        cadastroPage.cadastrar(cliente);

        assertEquals(msgEsperada, cadastroPage.getMensagemAlert());
    }

    @ParameterizedTest
    @MethodSource("selenium.dataHelpers.ClienteDataHelper#enderecosInvalidos")
    public void testeCadastroNovoEnderecosInvalidos(EnderecoVO endereco, String msgEsperada){
        HomePage homeCliente = this.realizarLoginCliente();

        PerfilPrincipalPage perfilCliente = homeCliente.acessarPerfilCliente();
        EnderecosPage enderecosPage = perfilCliente.acessarEnderecos();
        FormEnderecoPage formEnderecoPage = enderecosPage.adicionarEndereco();

        formEnderecoPage.salvarNovoEndereco(endereco);

        assertEquals(msgEsperada, UtilsTeste.getMensagemAlert(driver));
    }

    @Test
    public void testeDeleteEndereco(){
        EnderecoVO endereco = EnderecoVO.createEnderecoPadrao();
        HomePage homeCliente = this.realizarLoginCliente();

        PerfilPrincipalPage perfilCliente = homeCliente.acessarPerfilCliente();
        EnderecosPage enderecosPage = perfilCliente.acessarEnderecos();
        FormEnderecoPage formEnderecoPage = enderecosPage.adicionarEndereco();

        formEnderecoPage.salvarNovoEndereco(endereco);
        enderecosPage.removerEndereco(endereco);

        assertEquals("Perfil - Endereços", driver.getTitle());
    }

    @Test
    public void testeCadastroNovoCartao(CartaoVO cartao, String msgEsperada){
        HomePage homeCliente = this.realizarLoginCliente();

        PerfilPrincipalPage perfilCliente = homeCliente.acessarPerfilCliente();
        CartoesPage cartoesPage = perfilCliente.acessarCartoes();
        FormCartaoPage formCartaoPage = cartoesPage.adicionarCartao();

        formCartaoPage.salvarNovoCartao(cartao);

        assertEquals("Perfil - Cartões", driver.getTitle());
        assertEquals(msgEsperada, UtilsTeste.getMensagemAlert(driver));
    }

    @Test
    public void testeCadastroCartoesInvalidos(){
        HomePage homeCliente = this.realizarLoginCliente();

        PerfilPrincipalPage perfilCliente = homeCliente.acessarPerfilCliente();
        CartoesPage cartoesPage = perfilCliente.acessarCartoes();
        FormCartaoPage formCartaoPage = cartoesPage.adicionarCartao();

        CartaoVO cartao = CartaoVO.createCartaoPadrao();

        formCartaoPage.salvarNovoCartao(cartao);

        assertEquals("Perfil - Cartões", driver.getTitle());
    }

    @Test
    public void testeDeleteCartao(){
        CartaoVO cartao = CartaoVO.createCartaoPadrao();
        HomePage homeCliente = this.realizarLoginCliente();

        PerfilPrincipalPage perfilCliente = homeCliente.acessarPerfilCliente();
        CartoesPage cartoesPage = perfilCliente.acessarCartoes();
        FormCartaoPage formCartaoPage = cartoesPage.adicionarCartao();

        formCartaoPage.salvarNovoCartao(cartao);
        cartoesPage.removerCartao(cartao);

        assertEquals("Perfil - Cartões", driver.getTitle());
    }

}
