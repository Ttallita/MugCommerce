package selenium.scripts.unitario.cliente;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import selenium.dataHelpers.VOs.CartaoVO;
import selenium.dataHelpers.VOs.ClienteVO;
import selenium.dataHelpers.VOs.EnderecoVO;
import selenium.dataHelpers.VOs.UsuarioVO;
import selenium.pageModels.CadastroClientePage;
import selenium.pageModels.HomePage;
import selenium.pageModels.LoginPage;
import selenium.pageModels.formularios.FormCartaoPage;
import selenium.pageModels.formularios.FormEnderecoPage;
import selenium.pageModels.perfilCliente.CartoesPage;
import selenium.pageModels.perfilCliente.EnderecosPage;
import selenium.scripts.TesteAbstract;
import selenium.utils.UtilsTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteCadastroCliente extends TesteAbstract {

    @Override
    public void configurarCenarioTeste() { }

    @Test
    public void testeCadastroClienteValido() {
        driver.get(LINK_LOGIN);

        UsuarioVO usuario = new UsuarioVO();
        usuario.setEmail("login@teste.com");
        usuario.setSenha("Senha@123");
        usuario.setSenhaConfirmacao("Senha@123");

        EnderecoVO endereco = new EnderecoVO();
        endereco.setTpResidencia("Apartamento");
        endereco.setTpLogradouro("Avenida");
        endereco.setTpEndereco("Entrega");
        endereco.setLogradouro("Coronel da Cachoeira");
        endereco.setBairro("Santo José Bento");
        endereco.setNumeroEndereco("352");
        endereco.setCep("00412-536");
        endereco.setEstado("Alagoas");
        endereco.setCidade("Belém");
        endereco.setApelidoEndereco("Minha casa");
        endereco.setObservacaoEndereco("Próximo ao parque Pedreiro Verde");

        ClienteVO clienteCadastro = new ClienteVO();
        clienteCadastro.setNome("Joana");
        clienteCadastro.setSobrenome("das Dores");
        clienteCadastro.setCpf("966.673.660-89");
        clienteCadastro.setGenero("F");
        clienteCadastro.setDtNascimento("14/07/1985");
        clienteCadastro.setTelefone("1195627012");
        clienteCadastro.setUsuarioVO(usuario);
        clienteCadastro.setEnderecoVO(endereco);

        LoginPage loginPage = new LoginPage(driver);

        CadastroClientePage cadastroPage = loginPage.acessarCadastro();
        cadastroPage.cadastrar(clienteCadastro);

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

    @Test
    public void testeCadastroEndereco(){
        HomePage homeCliente = this.realizarLoginClientePadrao();

        EnderecosPage enderecosPage = homeCliente.acessarPerfilCliente().getSideBarCliente().acessarEnderecos();
        FormEnderecoPage formEnderecoPage = enderecosPage.adicionarEndereco();

        formEnderecoPage.salvarNovoEndereco(EnderecoVO.createEnderecoPadrao());

        assertEquals("Perfil - Endereços", driver.getTitle());
    }

    @ParameterizedTest
    @MethodSource("selenium.dataHelpers.ClienteDataHelper#enderecosInvalidos")
    public void testeCadastroEnderecosInvalidos(EnderecoVO endereco, String msgEsperada){
        HomePage homeCliente = this.realizarLoginClientePadrao();

        EnderecosPage enderecosPage = homeCliente.acessarPerfilCliente().getSideBarCliente().acessarEnderecos();
        FormEnderecoPage formEnderecoPage = enderecosPage.adicionarEndereco();

        formEnderecoPage.salvarNovoEndereco(endereco);

        assertEquals(msgEsperada, UtilsTeste.getMensagemAlert(driver));
    }

    @Test
    public void testeDeleteEndereco(){
        EnderecoVO endereco = EnderecoVO.createEnderecoPadrao();
        HomePage homeCliente = this.realizarLoginClientePadrao();

        EnderecosPage enderecosPage = homeCliente.acessarPerfilCliente().getSideBarCliente().acessarEnderecos();
        FormEnderecoPage formEnderecoPage = enderecosPage.adicionarEndereco();

        formEnderecoPage.salvarNovoEndereco(endereco);
        enderecosPage.removerEndereco(endereco);

        assertEquals("Perfil - Endereços", driver.getTitle());
    }

    @Test
    public void testeAlterarEndereco(){
        EnderecoVO endereco = EnderecoVO.createEnderecoPadrao();

        HomePage homeCliente = this.realizarLoginClientePadrao();

        EnderecosPage enderecosPage = homeCliente.acessarPerfilCliente().getSideBarCliente().acessarEnderecos();

        FormEnderecoPage formEnderecoPage = enderecosPage.adicionarEndereco();
        formEnderecoPage.salvarNovoEndereco(endereco);


        formEnderecoPage = enderecosPage.editarEndereco(endereco);

        endereco.setCep("95044-000");
        formEnderecoPage.editarEndereco(endereco);

        assertEquals("Perfil - Endereços", driver.getTitle());
    }

    @Test
    public void testeCadastroNovoCartao(){
        HomePage homeCliente = this.realizarLoginClientePadrao();

        CartoesPage cartoesPage = homeCliente.acessarPerfilCliente().getSideBarCliente().acessarCartoes();
        FormCartaoPage formCartaoPage = cartoesPage.adicionarCartao();

        formCartaoPage.salvarNovoCartao(CartaoVO.createCartaoPadrao());

        assertEquals("Perfil - Cartões", driver.getTitle());
    }

    @ParameterizedTest
    @MethodSource("selenium.dataHelpers.ClienteDataHelper#cartoesInvalidos")
    public void testeCadastroCartoesInvalidos(CartaoVO cartao, String msgEsperada){
        HomePage homeCliente = this.realizarLoginClientePadrao();

        CartoesPage cartoesPage = homeCliente.acessarPerfilCliente().getSideBarCliente().acessarCartoes();
        FormCartaoPage formCartaoPage = cartoesPage.adicionarCartao();

        formCartaoPage.salvarNovoCartao(cartao);

        assertEquals("Cadastro cartão", driver.getTitle());
        assertEquals(msgEsperada, UtilsTeste.getMensagemAlert(driver));
    }

    @Test
    public void testeDeleteCartao(){
        CartaoVO cartaoPreferencial = CartaoVO.createCartaoPadrao();

        CartaoVO cartao = CartaoVO.createCartaoPadrao();
        cartao.setNumCartao("1234 4567 8910 0000");

        HomePage homeCliente = this.realizarLoginClientePadrao();

        CartoesPage cartoesPage = homeCliente.acessarPerfilCliente().getSideBarCliente().acessarCartoes();

        FormCartaoPage formCartaoPage = cartoesPage.adicionarCartao();
        formCartaoPage.salvarNovoCartao(cartaoPreferencial);

        formCartaoPage = cartoesPage.adicionarCartao();
        formCartaoPage.salvarNovoCartao(cartao);

        cartoesPage.removerCartao(cartao);

        assertEquals("Perfil - Cartões", driver.getTitle());
    }

    @Test
    public void testeAlterarCartao(){
        CartaoVO cartao = CartaoVO.createCartaoPadrao();

        HomePage homeCliente = this.realizarLoginClientePadrao();

        CartoesPage cartoesPage = homeCliente.acessarPerfilCliente().getSideBarCliente().acessarCartoes();

        FormCartaoPage formCartaoPage = cartoesPage.adicionarCartao();
        formCartaoPage.salvarNovoCartao(cartao);


        formCartaoPage = cartoesPage.editarCartao(cartao);

        cartao.setNumCartao("1111 2222 3333 4444");
        formCartaoPage.editarCartao(cartao);

        assertEquals("Perfil - Cartões", driver.getTitle());
    }

}
