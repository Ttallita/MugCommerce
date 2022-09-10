package selenium.pageModels;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import selenium.pageModels.VOs.ClienteVO;
import selenium.pageModels.VOs.EnderecoVO;
import selenium.pageModels.VOs.UsuarioVO;

public class CadastroClientePage extends PageAbstract{

    // Formulário
    private final WebElement campoEmail;
    private final WebElement campoSenha;
    private final WebElement campoSenhaConfirmacao;
    private final WebElement campoNome;
    private final WebElement campoSobrenome;
    private final WebElement campoCpf;
    private final WebElement campoGenero;
    private final WebElement campoDtNascimento;
    private final WebElement campoTelefone;
    private final WebElement campoTpResidencia;
    private final WebElement campoTpLogradouro;
    private final WebElement campoLogradouro;
    private final WebElement campoBairro;
    private final WebElement campoNumeroEndereco;
    private final WebElement campoCep;
    private final WebElement campoEstado;
    private final WebElement campoCidade;
    private final WebElement campoApelidoEndereco;
    private final WebElement campoObservacaoEndereco;

    private final WebElement botaoCadastro;

    public static final String TITULO_PAGINA = "Cadastro - Cliente";

    public CadastroClientePage(WebDriver driver){
        super(driver, TITULO_PAGINA);

        campoEmail = driver.findElement(By.name("email"));
        campoSenha = driver.findElement(By.name("senha"));
        campoSenhaConfirmacao = driver.findElement(By.name("senhaConfirmacao"));
        campoNome = driver.findElement(By.name("nome"));
        campoSobrenome = driver.findElement(By.name("sobrenome"));
        campoCpf = driver.findElement(By.name("cpf"));
        campoGenero = driver.findElement(By.name("genero"));
        campoDtNascimento = driver.findElement(By.name("dtNascimento"));
        campoTelefone = driver.findElement(By.name("telefone"));
        campoTpResidencia = driver.findElement(By.name("tpResidencia"));
        campoTpLogradouro = driver.findElement(By.name("tpLogradouro"));
        campoLogradouro = driver.findElement(By.name("logradouro"));
        campoBairro = driver.findElement(By.name("bairro"));
        campoNumeroEndereco = driver.findElement(By.name("numeroEndereco"));
        campoCep = driver.findElement(By.name("cep"));
        campoEstado = driver.findElement(By.name("estado"));
        campoCidade = driver.findElement(By.name("cidade"));
        campoApelidoEndereco = driver.findElement(By.name("apelidoEndereco"));
        campoObservacaoEndereco = driver.findElement(By.name("observacaoEndereco"));

        botaoCadastro = driver.findElement(By.name("botaoCadastro"));
    }

    public CadastroClientePage cadastrar(ClienteVO cliente) throws InterruptedException {

        UsuarioVO usuario = cliente.getUsuarioVO();
        campoEmail.sendKeys(usuario.getEmail());
        campoSenha.sendKeys(usuario.getSenha());
        campoSenhaConfirmacao.sendKeys(usuario.getSenhaConfirmacao());

        campoNome.sendKeys(cliente.getNome());
        campoSobrenome.sendKeys(cliente.getSobrenome());
        campoCpf.sendKeys(cliente.getCpf());
        campoGenero.sendKeys(cliente.getGenero());
        selecionarData(cliente.getDtNascimento());
        campoTelefone.sendKeys(cliente.getTelefone());

        EnderecoVO endereco = cliente.getEnderecoVO();
        campoTpResidencia.sendKeys(endereco.getTpResidencia());
        campoTpLogradouro.sendKeys(endereco.getTpLogradouro());
        campoLogradouro.sendKeys(endereco.getLogradouro());
        campoBairro.sendKeys(endereco.getBairro());
        campoNumeroEndereco.sendKeys(endereco.getNumeroEndereco());
        campoCep.sendKeys(endereco.getCep());
        campoEstado.sendKeys(endereco.getEstado());

        if(!endereco.getEstado().isBlank()) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cidade"), "Mogi das Cruzes"));
            campoCidade.sendKeys(endereco.getCidade());
        }

        campoApelidoEndereco.sendKeys(endereco.getApelidoEndereco());
        campoObservacaoEndereco.sendKeys(endereco.getObservacaoEndereco());

        botaoCadastro.click();

        return this;
    }

    public String getMensagemAlert(){
        List<WebElement> elements = driver.findElement(By.className("alert")).findElements(By.tagName("li"));

        List<String> mensagens = elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        return String.join("\n", mensagens);
    }

    public void selecionarData(String dataNascimento) {
        campoDtNascimento.sendKeys("");
        Actions acoes = new Actions(driver);

        for(String a : dataNascimento.split("/"))
            acoes.sendKeys(Keys.chord(a));

        acoes.perform();
    }

}
