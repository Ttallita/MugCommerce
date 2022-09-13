package selenium.dataHelpers;

import selenium.dataHelpers.VOs.CartaoVO;
import selenium.dataHelpers.VOs.ClienteVO;
import selenium.dataHelpers.VOs.EnderecoVO;
import selenium.dataHelpers.VOs.UsuarioVO;
import utils.Utils;

import java.time.LocalDate;

public class ClienteDataHelper {

    private static Object[][] clientesInvalidos() {
        String dataAtual = Utils.formataLocalDateBR(LocalDate.now());

        UsuarioVO usuarioInvalida = UsuarioVO.createUsuarioClientePadrao();
        usuarioInvalida.setSenha("Senha123456");
        usuarioInvalida.setSenhaConfirmacao("Senha123456");

        UsuarioVO usuarioSenhaDiferente = UsuarioVO.createUsuarioClientePadrao();
        usuarioSenhaDiferente.setSenhaConfirmacao("SenhaDiferente@123");


        ClienteVO cliente1 = ClienteVO.createClienteVOPadrao();
        cliente1.setUsuarioVO(usuarioInvalida);

        ClienteVO cliente2 = ClienteVO.createClienteVOPadrao();
        cliente2.setUsuarioVO(usuarioSenhaDiferente);

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

    private static Object[][] enderecosInvalidos(){
        EnderecoVO endereco1 = EnderecoVO.createEnderecoPadrao();
        EnderecoVO endereco2 = EnderecoVO.createEnderecoVazio();

        endereco1.setNumeroEndereco("");

        return new Object[][]{
                {endereco1, "O campo número é obrigatório"},
                {endereco2, "Selecione um tipo de endereço valido"}
        };
    }

    private static Object[][] cartoesInvalidos(){
        CartaoVO cartao1 = CartaoVO.createCartaoPadrao();
        CartaoVO cartao2 = CartaoVO.createCartaoPadrao();
        CartaoVO cartao3 = CartaoVO.createCartaoPadrao();

        cartao1.setNumCartao("");
        cartao2.setDtValidade("10/2000");
        cartao3.setDtValidade("33/2030");

        return new Object[][]{
                {cartao1, "Insira um número de cartão de crédito válido"},
                {cartao2, "Ano de validade menor que o ano atual"},
                {cartao3, "Mês de validade inválido"}
        };
    }

}
