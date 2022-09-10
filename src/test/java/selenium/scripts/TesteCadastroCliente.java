package selenium.scripts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import selenium.pageModels.CadastroClientePage;
import selenium.pageModels.ClienteVO;
import selenium.pageModels.EnderecoVO;
import selenium.pageModels.LoginPage;
import utils.Utils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteCadastroCliente extends TesteAbstract{

    @Test
    public void testeCadastroClienteValido() throws InterruptedException {
        driver.get("http://localhost:8080/emug/login.jsp");

        ClienteVO cliente = ClienteVO.createClienteVOPadrao();
        LoginPage loginPage = new LoginPage(driver);

        CadastroClientePage cadastroPage = loginPage.acessarCadastro();
        cadastroPage.cadastrar(cliente);

        assertEquals("Cadastrado com sucesso. Clique aqui para logar", cadastroPage.getMensagemAlert());
    }

    private static Object[][] clientesInvalidos() {
        String dataAtual = Utils.formataLocalDateBR(LocalDate.now());

        ClienteVO cliente1 = ClienteVO.createClienteVOPadrao();
        cliente1.setSenha("Senha123456");
        cliente1.setSenhaConfirmacao("Senha123456");

        ClienteVO cliente2 = ClienteVO.createClienteVOPadrao();
        cliente2.setSenhaConfirmacao("senhaDiferente123@");

        ClienteVO cliente3 = ClienteVO.createClienteVOPadrao();
        cliente3.setCpf("123.456.789-10");

        ClienteVO cliente4 = ClienteVO.createClienteVOPadrao();
        cliente4.setDtNascimento(dataAtual);

        ClienteVO cliente5 = ClienteVO.createClienteVOPadrao();
        cliente5.setEnderecoVO(EnderecoVO.createEnderecoVazio());

        ClienteVO cliente6 = ClienteVO.createClienteVOPadrao();
        cliente6.setNome("");

        return new Object[][]{
                {cliente1, "Senha inválida"},
                {cliente2, "As senhas não coincidem"},
                {cliente3, "Digite um cpf válido"},
                {cliente4, "A data de nascimento não pode ser maior ou igual que a data atual"},
                {cliente5, "O campo tipo de residência é obrigatório"},
                {cliente6, "Digite um nome válido"}
        };
    }

    @ParameterizedTest
    @MethodSource("clientesInvalidos")
    public void testeCadastroClienteInvalido(ClienteVO cliente, String msgEsperada) throws InterruptedException {
        driver.get("http://localhost:8080/emug/login.jsp");
        LoginPage loginPage = new LoginPage(driver);

        CadastroClientePage cadastroPage = loginPage.acessarCadastro();
        cadastroPage.cadastrar(cliente);

        assertEquals(msgEsperada, cadastroPage.getMensagemAlert());
    }

}
