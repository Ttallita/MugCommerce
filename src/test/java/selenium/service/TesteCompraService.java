package selenium.service;

import org.openqa.selenium.WebDriver;
import selenium.dataHelpers.ClienteDataHelper;
import selenium.dataHelpers.VOs.CartaoVO;
import selenium.dataHelpers.VOs.EnderecoVO;
import selenium.pageModels.FinalizarCompraPage;
import selenium.pageModels.HomePage;
import selenium.pageModels.components.HeaderClienteComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TesteCompraService {

    public static HomePage realizarCompra(WebDriver driver, HeaderClienteComponent headerCliente) throws InterruptedException {
        // -------- Criando carrinho
        TesteCarrinhoService.populaCarrinho(driver, headerCliente);

        FinalizarCompraPage finalizarPage = headerCliente.acessarCarrinho().finalizarCompra();

        // --------- Selecionando cartões
        List<CartaoVO> cartoes = new ArrayList<>(ClienteDataHelper.getCartoesPreviamenteCadastrados());
        Collections.shuffle(cartoes);

        finalizarPage.abrirModalAlterarFormaPagamento().alterarCartoes(List.of(cartoes.get(0)));

        // ---------- Selecionando endereços
        List<EnderecoVO> enderecosEntrega =  new ArrayList<>(ClienteDataHelper.getEnderecosEntregaPreviamenteCadastrados());
        Collections.shuffle(enderecosEntrega);

        EnderecoVO enderecoEntrega = enderecosEntrega.get(0);
        finalizarPage.abrirModalAlterarEnderecoEntrega().alterarEndereco(enderecoEntrega);

        Thread.sleep(1000L);

        if (!enderecoEntrega.getTpEndereco().equals("Entrega e cobrança")) {
            List<EnderecoVO> enderecosCobranca = new ArrayList<>(ClienteDataHelper.getEnderecosCobrancaPreviamenteCadastrados());
            Collections.shuffle(enderecosCobranca);

            finalizarPage.abrirModalAlterarEnderecoCobranca().alterarEndereco(enderecosCobranca.get(0));
        }

        Thread.sleep(1000L);

        return finalizarPage.confirmarPedido();
    }
}
