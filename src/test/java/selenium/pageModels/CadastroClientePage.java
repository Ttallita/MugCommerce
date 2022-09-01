package selenium.pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CadastroClientePage extends PageAbstract{

    // Formulário
    private WebElement campoEmail;
    private WebElement campoSenha;
    private WebElement campoSenhaConfirmacao;
    private WebElement campoNome;
    private WebElement campoSobrenome;
    private WebElement campoCpf;
    private WebElement campoGenero;
    private WebElement campoDtNascimento;
    private WebElement campoTelefone;
    private WebElement campoTpResidencia;
    private WebElement campoTpLogradouro;
    private WebElement campoLogradouro;
    private WebElement campoBairro;
    private WebElement campoNumeroEndereco;
    private WebElement campoCep;
    private WebElement campoPais;
    private WebElement campoEstado;
    private WebElement campoCidade;
    private WebElement campoApelidoEndereco;
    private WebElement campoObservacaoEndereco;

    private WebElement botaoCadastro;

    public static final String TITULO_PAGINA = "Cadastro";

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
        campoPais = driver.findElement(By.name("pais"));
        campoEstado = driver.findElement(By.name("estado"));
        campoCidade = driver.findElement(By.name("cidade"));
        campoApelidoEndereco = driver.findElement(By.name("apelidoEndereco"));
        campoObservacaoEndereco = driver.findElement(By.name("observacaoEndereco"));

        botaoCadastro = driver.findElement(By.name("botaoCadastro"));
    }

    public LoginPage cadastrar(ClienteVO cliente) {

        campoEmail.sendKeys(cliente.getEmail());
        campoSenha.sendKeys(cliente.getSenha());
        campoSenhaConfirmacao.sendKeys(cliente.getSenhaConfirmacao());
        campoNome.sendKeys(cliente.getNome());
        campoSobrenome.sendKeys(cliente.getSobrenome());
        campoCpf.sendKeys(cliente.getCpf());
        campoGenero.sendKeys(cliente.getGenero());
        campoDtNascimento.sendKeys(cliente.getDtNascimento());
        campoTelefone.sendKeys(cliente.getTelefone());
        campoTpResidencia.sendKeys(cliente.getTpResidencia());
        campoTpLogradouro.sendKeys(cliente.getTpLogradouro());
        campoLogradouro.sendKeys(cliente.getLogradouro());
        campoBairro.sendKeys(cliente.getBairro());
        campoNumeroEndereco.sendKeys(cliente.getNumeroEndereco());
        campoCep.sendKeys(cliente.getCep());
        campoPais.sendKeys(cliente.getPais());
        campoEstado.sendKeys(cliente.getEstado());
        campoCidade.sendKeys(cliente.getCidade());
        campoApelidoEndereco.sendKeys(cliente.getApelidoEndereco());
        campoObservacaoEndereco.sendKeys(cliente.getObservacaoEndereco());

        botaoCadastro.click();

        return new LoginPage(driver);
    }

}
