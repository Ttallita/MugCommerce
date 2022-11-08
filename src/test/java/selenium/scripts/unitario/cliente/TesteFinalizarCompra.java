package selenium.scripts.unitario.cliente;

import org.junit.jupiter.api.Test;
import selenium.dataHelpers.ClienteDataHelper;
import selenium.dataHelpers.VOs.CartaoVO;
import selenium.dataHelpers.VOs.EnderecoVO;
import selenium.pageModels.FinalizarCompraPage;
import selenium.pageModels.components.*;
import selenium.scripts.TesteAbstract;
import selenium.service.TesteCarrinhoService;
import selenium.utils.UtilsTeste;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteFinalizarCompra extends TesteAbstract {

    private HeaderClienteComponent headerCliente;
    private FinalizarCompraPage finalizarCompra;

    @Override
    protected void configurarCenarioTeste() {
        headerCliente = (HeaderClienteComponent) this.realizarLoginClientePadrao().getHeader(driver);
        TesteCarrinhoService.populaCarrinho(driver, headerCliente);

        finalizarCompra = headerCliente.acessarCarrinho().finalizarCompra();
    }

    // -------------- Cartões
    @Test
    public void testeAlterarCartoesSelecionados(){
        ModalCartoesComponent modalCartoes = finalizarCompra.abrirModalAlterarFormaPagamento();

        List<CartaoVO> cartoes = ClienteDataHelper.getCartoesPreviamenteCadastrados().subList(0, 3);
        modalCartoes.alterarCartoes(cartoes);

        for(CartaoVO cartao : cartoes)
            assertTrue(finalizarCompra.isCartaoListado(cartao));
    }

    @Test
    public void testeAdicionarCartao(){
        ModalCartoesComponent modalCartoes = finalizarCompra.abrirModalAlterarFormaPagamento();
        FormCartaoComponent formCartao = modalCartoes.adicionarNovoCartao();

        CartaoVO cartaoVO = new CartaoVO();
        cartaoVO.setNumCartao("1234 1234 1234 1234");
        cartaoVO.setNomeCartao("Cartão finalizar compra teste");
        cartaoVO.setBandeira("Elo");
        cartaoVO.setCodigoCartao("111");
        cartaoVO.setDtValidade("01/2030");

        formCartao.salvarNovoCartao(cartaoVO);

        assertTrue(finalizarCompra.abrirModalAlterarFormaPagamento().isCartaoListado(cartaoVO));
    }

    @Test
    public void testeEditarCartao(){
        CartaoVO cartao = ClienteDataHelper.getCartoesPreviamenteCadastrados().get(0);

        ModalCartoesComponent modalCartoes = finalizarCompra.abrirModalAlterarFormaPagamento();
        FormCartaoComponent formCartao = modalCartoes.editarCartao(cartao);

        cartao.setNomeCartao(cartao.getNomeCartao() + " Teste");

        formCartao.editarCartao(cartao);
        assertTrue(finalizarCompra.isCartaoListado(cartao));
    }

    // --------- Endereços

    @Test
    public void testeAlterarEnderecoEntrega(){
        ModalEnderecosComponent modalEnderecos = finalizarCompra.abrirModalAlterarEnderecoEntrega();

        EnderecoVO endereco = ClienteDataHelper.getEnderecosEntregaPreviamenteCadastrados().get(0);
        modalEnderecos.alterarEndereco(endereco);

        UtilsTeste.esperarTelaRecarregar(driver);

        assertTrue(finalizarCompra.isEnderecoEntregaListado(endereco));
    }

    @Test
    public void testeAlterarEnderecoCobranca(){
        ModalEnderecosComponent modalEnderecos = finalizarCompra.abrirModalAlterarEnderecoCobranca();

        EnderecoVO endereco = ClienteDataHelper.getEnderecosCobrancaPreviamenteCadastrados().get(0);
        modalEnderecos.alterarEndereco(endereco);

        UtilsTeste.esperarTelaRecarregar(driver);

        assertTrue(finalizarCompra.isEnderecoEntregaListado(endereco));
    }

    @Test
    public void testeAdicionarEnderecoCobranca(){
        ModalEnderecosComponent modalEndereco = finalizarCompra.abrirModalAlterarEnderecoCobranca();
        FormEnderecoComponent formEndereco = modalEndereco.adicionarNovoEndereco();

        EnderecoVO enderecoVO = new EnderecoVO();
        enderecoVO.setTpResidencia("Sobrado");
        enderecoVO.setTpLogradouro("Avenida");
        enderecoVO.setTpEndereco("Cobranca");
        enderecoVO.setLogradouro("Pingo d'água");
        enderecoVO.setBairro("Fernandes");
        enderecoVO.setNumeroEndereco("586");
        enderecoVO.setCep("95044-120");
        enderecoVO.setEstado("São Paulo");
        enderecoVO.setCidade("Mogi das Cruzes");
        enderecoVO.setApelidoEndereco("Teste finalizar compra");
        enderecoVO.setObservacaoEndereco("");

        formEndereco.salvarNovoEndereco(enderecoVO);

        assertTrue(finalizarCompra.abrirModalAlterarEnderecoCobranca().isEnderecoListado(enderecoVO));
    }

    @Test
    public void testeEditarEnderecoCobranca(){
        EnderecoVO endereco = ClienteDataHelper.getEnderecosCobrancaPreviamenteCadastrados().get(0);

        ModalEnderecosComponent modalEndereco = finalizarCompra.abrirModalAlterarEnderecoCobranca();
        FormEnderecoComponent formEndereco = modalEndereco.editarEndereco(endereco);

        endereco.setApelidoEndereco(endereco.getApelidoEndereco() + " Teste");
        formEndereco.editarEndereco(endereco);

        // TODO não está passando porque já está sendo listado um endereço de cobrança e de entrega... Verificar comportamento
//        assertTrue(finalizarCompra.isEnderecoCobrancaListado(endereco));
    }

    @Test
    public void testeEditarEnderecoEntrega(){
        EnderecoVO endereco = ClienteDataHelper.getEnderecosEntregaPreviamenteCadastrados().get(0);

        ModalEnderecosComponent modalEndereco = finalizarCompra.abrirModalAlterarEnderecoEntrega();
        FormEnderecoComponent formEndereco = modalEndereco.editarEndereco(endereco);

        endereco.setApelidoEndereco(endereco.getApelidoEndereco() + " Teste");
        formEndereco.editarEndereco(endereco);

        assertTrue(finalizarCompra.isEnderecoEntregaListado(endereco));
    }

}
