package selenium.scripts;

import org.junit.jupiter.api.Test;
import selenium.dataHelpers.ClienteDataHelper;
import selenium.dataHelpers.ProdutoDataHelper;
import selenium.dataHelpers.VOs.CartaoVO;
import selenium.dataHelpers.VOs.EnderecoVO;
import selenium.pageModels.FinalizarCompraPage;
import selenium.pageModels.ProdutoPage;
import selenium.pageModels.components.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteFinalizarCompra extends TesteAbstract{

    private HeaderClienteComponent headerCliente;
    private FinalizarCompraPage finalizarCompra;

    @Override
    void configurarCenarioTeste() {

        headerCliente = (HeaderClienteComponent) this.realizarLoginClientePadrao().getHeader(driver);

        // Adiciona produtos ao carrinho
        List<String> nomesProdutos = ProdutoDataHelper.nomesProdutos();
        nomesProdutos.forEach(nomeProduto -> {
            headerCliente.pesquisar(nomeProduto);
            ProdutoPage.abrirPaginaProduto(nomeProduto).adicionarProdutoCarrinho();
        });

        finalizarCompra = headerCliente.acessarCarrinho().finalizarCompra();
    }

    @Test
    public void testeAlterarCartoesSelecionados(){
        ModalCartoesComponent modalCartoes = finalizarCompra.abrirModalAlterarFormaPagamento();

        List<CartaoVO> cartoes = ClienteDataHelper.getCartoesPreviamenteCadastrados().subList(0, 3);
        for(CartaoVO cartao : cartoes){
            modalCartoes.selecionarCartao(cartao);
        }

        modalCartoes.alterarItensSelecionados();

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

    }

    @Test
    public void testeAdicionarEnderecoCobranca(){
        ModalEnderecosComponent modalEndereco = finalizarCompra.abrirModalAlterarEnderecoCobranca();
        FormEnderecoComponent formEndereco = modalEndereco.adicionarNovoEndereco();

        EnderecoVO enderecoVO = new EnderecoVO();
        enderecoVO.setTpResidencia("Sobrado");
        enderecoVO.setTpLogradouro("Avenida");
        enderecoVO.setTpEndereco("Entrega");
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
    public void testeEditarEndereco(){
        EnderecoVO endereco = ClienteDataHelper.getEnderecosPreviamenteCadastrados().get(0);

        ModalEnderecosComponent modalEndereco = finalizarCompra.abrirModalAlterarEnderecoCobranca();
        FormEnderecoComponent formEndereco = modalEndereco.editarEndereco(endereco);
    }



}
