package selenium.dataHelpers;

import selenium.dataHelpers.VOs.CartaoVO;
import selenium.dataHelpers.VOs.ClienteVO;
import selenium.dataHelpers.VOs.EnderecoVO;
import selenium.dataHelpers.VOs.UsuarioVO;
import utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Cartões previamente salvos no banco de dados
     */
    public static List<CartaoVO> getCartoesPreviamenteCadastrados() {
        List<CartaoVO> cartoesVO = new ArrayList<>();
        CartaoVO cartaoVO1 = new CartaoVO(); // Cartão preferencial
        cartaoVO1.setNumCartao("1111 1111 1111 1111");
        cartaoVO1.setNomeCartao("Alexandre Portugal");
        cartaoVO1.setBandeira("Elo");
        cartaoVO1.setCodigoCartao("111");
        cartaoVO1.setDtValidade("01/2031");

        CartaoVO cartaoVO2 = new CartaoVO();
        cartaoVO2.setNumCartao("2222 2222 2222 2222");
        cartaoVO2.setNomeCartao("Bianca Jade");
        cartaoVO2.setBandeira("Mastercard");
        cartaoVO2.setCodigoCartao("222");
        cartaoVO2.setDtValidade("02/2032");

        CartaoVO cartaoVO3 = new CartaoVO();
        cartaoVO3.setNumCartao("3333 3333 3333 3333");
        cartaoVO3.setNomeCartao("Carlos Sergipe");
        cartaoVO3.setBandeira("American Express");
        cartaoVO3.setCodigoCartao("333");
        cartaoVO3.setDtValidade("03/2033");

        CartaoVO cartaoVO4 = new CartaoVO();
        cartaoVO4.setNumCartao("4444 4444 4444 4444");
        cartaoVO4.setNomeCartao("Daniel Salvador");
        cartaoVO4.setBandeira("Hipercard");
        cartaoVO4.setCodigoCartao("444");
        cartaoVO4.setDtValidade("04/2034");

        CartaoVO cartaoVO5 = new CartaoVO();
        cartaoVO5.setNumCartao("5555 5555 5555 5555");
        cartaoVO5.setNomeCartao("Eduarda Salmão");
        cartaoVO5.setBandeira("Visa");
        cartaoVO5.setCodigoCartao("555");
        cartaoVO5.setDtValidade("05/2035");

        CartaoVO cartaoVO6 = new CartaoVO();
        cartaoVO6.setNumCartao("6666 6666 6666 6666");
        cartaoVO6.setNomeCartao("Fábio Dom Jão");
        cartaoVO6.setBandeira("Elo");
        cartaoVO6.setCodigoCartao("666");
        cartaoVO6.setDtValidade("06/2036");

        cartoesVO.add(cartaoVO1);
        cartoesVO.add(cartaoVO2);
        cartoesVO.add(cartaoVO3);
        cartoesVO.add(cartaoVO4);
        cartoesVO.add(cartaoVO5);
        cartoesVO.add(cartaoVO6);

        return cartoesVO;
    }

    /**
     * Endereços previamente salvos no banco de dados
     */
    public static List<EnderecoVO> getEnderecosCobrancaPreviamenteCadastrados() {
        List<EnderecoVO> enderecoVOS = new ArrayList<>();

        EnderecoVO enderecoVO1 = new EnderecoVO();
        enderecoVO1.setTpResidencia("Apartamento");
        enderecoVO1.setTpLogradouro("Alameda");
        enderecoVO1.setTpEndereco("Entrega e cobrança");
        enderecoVO1.setLogradouro("Águas sagradas");
        enderecoVO1.setBairro("Amélia Soares");
        enderecoVO1.setNumeroEndereco("111");
        enderecoVO1.setCep("11111-111");
        enderecoVO1.setEstado("Acre");
        enderecoVO1.setCidade("Acrelândia");
        enderecoVO1.setApelidoEndereco("Apartamento A");
        enderecoVO1.setObservacaoEndereco("");

        EnderecoVO enderecoVO2 = new EnderecoVO();
        enderecoVO2.setTpResidencia("Sobrado");
        enderecoVO2.setTpLogradouro("Beco");
        enderecoVO2.setTpEndereco("Cobrança");
        enderecoVO2.setLogradouro("Bonda dourada");
        enderecoVO2.setBairro("Barões");
        enderecoVO2.setNumeroEndereco("222");
        enderecoVO2.setCep("22222-222");
        enderecoVO2.setEstado("Bahia");
        enderecoVO2.setCidade("Bonito");
        enderecoVO2.setApelidoEndereco("Sobrado B");
        enderecoVO2.setObservacaoEndereco("");

        EnderecoVO enderecoVO4 = new EnderecoVO();
        enderecoVO4.setTpResidencia("Casa");
        enderecoVO4.setTpLogradouro("Distrito");
        enderecoVO4.setTpEndereco("Entrega e cobrança");
        enderecoVO4.setLogradouro("Dom Terciário Quarto");
        enderecoVO4.setBairro("Dama Claudite");
        enderecoVO4.setNumeroEndereco("444");
        enderecoVO4.setCep("44444-444");
        enderecoVO4.setEstado("Distrito Federal");
        enderecoVO4.setCidade("Brasília");
        enderecoVO4.setApelidoEndereco("Casa D");
        enderecoVO4.setObservacaoEndereco("");

        enderecoVOS.add(enderecoVO1);
        enderecoVOS.add(enderecoVO2);
        enderecoVOS.add(enderecoVO4);

        return enderecoVOS;
    }

    /**
     * Endereços previamente salvos no banco de dados
     */
    public static List<EnderecoVO> getEnderecosEntregaPreviamenteCadastrados() {
        List<EnderecoVO> enderecoVOS = new ArrayList<>();

        EnderecoVO enderecoCadastro = new EnderecoVO();
        enderecoCadastro.setTpResidencia("Sobrado");
        enderecoCadastro.setTpLogradouro("Avenida");
        enderecoCadastro.setTpEndereco("Entrega e cobrança");
        enderecoCadastro.setLogradouro("Pingo d'água");
        enderecoCadastro.setBairro("Fernandes");
        enderecoCadastro.setNumeroEndereco("586");
        enderecoCadastro.setCep("95044-120");
        enderecoCadastro.setEstado("São Paulo");
        enderecoCadastro.setCidade("Mogi das Cruzes");
        enderecoCadastro.setApelidoEndereco("Minha casa");
        enderecoCadastro.setObservacaoEndereco("");

        EnderecoVO enderecoVO1 = new EnderecoVO();
        enderecoVO1.setTpResidencia("Apartamento");
        enderecoVO1.setTpLogradouro("Alameda");
        enderecoVO1.setTpEndereco("Entrega e cobrança");
        enderecoVO1.setLogradouro("Águas sagradas");
        enderecoVO1.setBairro("Amélia Soares");
        enderecoVO1.setNumeroEndereco("111");
        enderecoVO1.setCep("11111-111");
        enderecoVO1.setEstado("Acre");
        enderecoVO1.setCidade("Acrelândia");
        enderecoVO1.setApelidoEndereco("Apartamento A");
        enderecoVO1.setObservacaoEndereco("");

        EnderecoVO enderecoVO3 = new EnderecoVO();
        enderecoVO3.setTpResidencia("Casa");
        enderecoVO3.setTpLogradouro("Comunidade");
        enderecoVO3.setTpEndereco("Entrega");
        enderecoVO3.setLogradouro("Coroa Lorenzo");
        enderecoVO3.setBairro("Carmo Nogueira");
        enderecoVO3.setNumeroEndereco("333");
        enderecoVO3.setCep("33333-333");
        enderecoVO3.setEstado("Ceará");
        enderecoVO3.setCidade("Camocim");
        enderecoVO3.setApelidoEndereco("Casa C");
        enderecoVO3.setObservacaoEndereco("");

        EnderecoVO enderecoVO4 = new EnderecoVO();
        enderecoVO4.setTpResidencia("Casa");
        enderecoVO4.setTpLogradouro("Distrito");
        enderecoVO4.setTpEndereco("Entrega e cobrança");
        enderecoVO4.setLogradouro("Dom Terciário Quarto");
        enderecoVO4.setBairro("Dama Claudite");
        enderecoVO4.setNumeroEndereco("444");
        enderecoVO4.setCep("44444-444");
        enderecoVO4.setEstado("Distrito Federal");
        enderecoVO4.setCidade("Brasília");
        enderecoVO4.setApelidoEndereco("Casa D");
        enderecoVO4.setObservacaoEndereco("");

        enderecoVOS.add(enderecoCadastro);
        enderecoVOS.add(enderecoVO1);
        enderecoVOS.add(enderecoVO3);
        enderecoVOS.add(enderecoVO4);

        return enderecoVOS;
    }

}
