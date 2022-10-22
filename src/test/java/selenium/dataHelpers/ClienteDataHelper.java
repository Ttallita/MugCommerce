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

//    public static Object[][] cartoesValidos(){
//
//        List<CartaoVO> cartoesVO = new ArrayList<>();
//        addCartoesFinalizarCompra(cartoesVO);
//
//        return new Object[][]{
//                {cartoesVO.get(0)},
//                {cartoesVO.get(1)},
//                {cartoesVO.get(2)},
//                {cartoesVO.get(3)},
//                {cartoesVO.get(4)},
//                {cartoesVO.get(5)}
//        };
//    }

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

}
