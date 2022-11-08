package selenium.service;

import org.openqa.selenium.WebDriver;
import selenium.dataHelpers.ProdutoDataHelper;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.pageModels.perfilCliente.CarrinhoPage;
import selenium.scripts.unitario.cliente.TesteCarrinho;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TesteCarrinhoService {

    private static final Random rand = new Random();

    public static void populaCarrinho(WebDriver driver, HeaderClienteComponent headerCliente) {
        List<String> nomesProdutos = new ArrayList<>(ProdutoDataHelper.nomesTodosProdutos());
        Collections.shuffle(nomesProdutos);

        nomesProdutos.subList(0, 1).forEach( nomeProduto -> {
            CarrinhoPage carrinho = TesteCarrinho.adicionaProdutoAoCarrinho(driver, headerCliente, nomeProduto);

            if (carrinho != null)
                carrinho.alterarQuantidadeProduto(rand.nextInt(5), nomeProduto);
        });
    }

}
