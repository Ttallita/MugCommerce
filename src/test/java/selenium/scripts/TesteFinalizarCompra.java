package selenium.scripts;

import org.junit.jupiter.api.Test;
import selenium.dataHelpers.ClienteDataHelper;
import selenium.dataHelpers.ProdutoDataHelper;
import selenium.dataHelpers.VOs.CartaoVO;
import selenium.pageModels.FinalizarCompraPage;
import selenium.pageModels.ProdutoPage;
import selenium.pageModels.components.FormCartaoComponent;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.pageModels.components.ModalCartoesComponent;
import selenium.pageModels.perfilCliente.CarrinhoPage;

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

        modalCartoes.alterarCartoesSelecionados();

        for(CartaoVO cartao : cartoes)
            assertTrue(finalizarCompra.isCartaoListado(cartao));

        /*
            editar cartão
            selecionar mais de um cartão
         */
    }

    @Test
    public void testeAdicionarCartao(){
        ModalCartoesComponent modalCartoes = finalizarCompra.abrirModalAlterarFormaPagamento();
        FormCartaoComponent formCartao = modalCartoes.adicionarNovoCartao();

        CartaoVO cartaoVO = new CartaoVO(); // Cartão preferencial
        cartaoVO.setNumCartao("1234 1234 1234 1234");
        cartaoVO.setNomeCartao("Cartão finalizar compra teste");
        cartaoVO.setBandeira("Elo");
        cartaoVO.setCodigoCartao("111");
        cartaoVO.setDtValidade("01/2030");

        formCartao.salvarNovoCartao(cartaoVO);

        assertTrue(finalizarCompra.abrirModalAlterarFormaPagamento().isCartaoListado(cartaoVO));
    }

//    @Test
//    public void testeAdicionarCartao(){
//        ModalCartoesComponent modalCartoes = finalizarCompra.abrirModalAlterarFormaPagamento();
//        FormCartaoComponent formCartao = modalCartoes.adicionarNovoCartao();
//
//        CartaoVO cartaoVO = new CartaoVO(); // Cartão preferencial
//        cartaoVO.setNumCartao("1234 1234 1234 1234");
//        cartaoVO.setNomeCartao("Cartão finalizar compra teste");
//        cartaoVO.setBandeira("Elo");
//        cartaoVO.setCodigoCartao("111");
//        cartaoVO.setDtValidade("01/2030");
//
//        formCartao.salvarNovoCartao(cartaoVO);
//
//        assertTrue(finalizarCompra.abrirModalAlterarFormaPagamento().isCartaoListado(cartaoVO));
//    }

}
