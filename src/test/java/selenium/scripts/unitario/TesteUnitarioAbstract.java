package selenium.scripts.unitario;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.dataHelpers.ClienteDataHelper;
import selenium.dataHelpers.ProdutoDataHelper;
import selenium.dataHelpers.VOs.CartaoVO;
import selenium.dataHelpers.VOs.EnderecoVO;
import selenium.dataHelpers.VOs.UsuarioVO;
import selenium.pageModels.FinalizarCompraPage;
import selenium.pageModels.HomePage;
import selenium.pageModels.LoginPage;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.pageModels.components.ModalCartoesComponent;
import selenium.pageModels.components.ModalEnderecosComponent;
import selenium.pageModels.perfilCliente.CarrinhoPage;
import selenium.scripts.unitario.cliente.TesteCarrinho;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * ATENÇÃO! Lembrar de inserir o cliente padrão de login do arquivo banco_emug.sql
 */
public abstract class TesteUnitarioAbstract {

    protected WebDriver driver;

    protected static final String LINK_LOGIN = "http://localhost:8080/emug/login.jsp";

    protected abstract void configurarCenarioTeste() throws InterruptedException;

    private final Random rand = new Random();

    @BeforeEach
    public void setup() throws InterruptedException {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        configurarCenarioTeste();
    }

    @AfterEach
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    public HomePage realizarLoginClientePadrao(){
        driver.get(LINK_LOGIN);
        LoginPage loginPage = new LoginPage(driver);

        return loginPage.logar(UsuarioVO.createUsuarioClientePadrao());
    }

    public HomePage realizarLoginAdmPadrao() {
        driver.get(LINK_LOGIN);
        var loginPage = new LoginPage(driver);

        return loginPage.logar(UsuarioVO.createUsuarioAdmPadrao());
    }

    public HomePage realizarCompra(HeaderClienteComponent headerCliente) throws InterruptedException {
        // -------- Criando carrinho
        populaCarrinho(headerCliente);

        FinalizarCompraPage finalizarPage = headerCliente.acessarCarrinho().finalizarCompra();

        // --------- Selecionando cartões
        List<CartaoVO> cartoes = new ArrayList<>(ClienteDataHelper.getCartoesPreviamenteCadastrados());
        Collections.shuffle(cartoes);

        finalizarPage.abrirModalAlterarFormaPagamento().alterarCartoes(cartoes.subList(0, rand.nextInt(3)));

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

    public void populaCarrinho(HeaderClienteComponent headerCliente) {
        List<String> nomesProdutos = new ArrayList<>(ProdutoDataHelper.nomesTodosProdutos());
        Collections.shuffle(nomesProdutos);

        nomesProdutos.subList(0, 1).forEach( nomeProduto -> {
            CarrinhoPage carrinho = TesteCarrinho.adicionaProdutoAoCarrinho(driver, headerCliente, nomeProduto);
            carrinho.alterarQuantidadeProduto(rand.nextInt(5), nomeProduto);
        });
    }

}
