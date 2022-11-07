package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.dataHelpers.VOs.ProdutoVO;

import java.io.File;
import java.util.Objects;
import java.util.Random;

public class FormProdutoComponent {
    private final WebElement campoNome;
    private final WebElement campoValorCompra;
    private final WebElement campoCodBarras;
    private final WebElement campoMaterial;
    private final WebElement campoFabricante;
    private final WebElement campoPrecificao;
    private final WebElement campoCategorias;
    private final WebElement campoDescricao;
    private final WebElement campoImagem;
    private final WebElement campoAtivo;

    private final WebElement botaoCadastro;

    private final WebDriver driver;

    public FormProdutoComponent(WebDriver driver) {
        this.campoNome = driver.findElement(By.name("nomeProduto"));
        this.campoValorCompra = driver.findElement(By.name("valorCompra"));
        this.campoCodBarras = driver.findElement(By.name("codBarras"));
        this.campoMaterial = driver.findElement(By.name("material"));
        this.campoFabricante = driver.findElement(By.name("fabricante"));
        this.campoPrecificao = driver.findElement(By.name("grupoPrecificacao"));
        this.campoCategorias = driver.findElement(By.name("categorias"));
        this.campoDescricao = driver.findElement(By.name("descricao"));
        this.campoImagem = driver.findElement(By.id("imgProduto"));
        this.campoAtivo = driver.findElement(By.name("isAtivo"));

        this.botaoCadastro = driver.findElement(By.name("botaoCadastro"));

        this.driver = driver;
    }

    public void salvarNovoProduto(ProdutoVO produto) {
        campoNome.clear();
        campoValorCompra.clear();
        campoCodBarras.clear();
        campoDescricao.clear();
        campoImagem.clear();

        File randomImageFile = getRandomImageFile();
        produto.setImagem(randomImageFile.getAbsolutePath());

        campoNome.sendKeys(produto.getNome());
        campoValorCompra.sendKeys(produto.getValorCompra() == null ? "" : produto.getValorCompra().toString().replace(".", ","));
        campoCodBarras.sendKeys(produto.getCodBarras());
        campoMaterial.sendKeys(produto.getMaterial());
        campoFabricante.sendKeys(produto.getFabricante());
        campoPrecificao.sendKeys(produto.getGrupoPrecificao());
        campoCategorias.sendKeys(produto.getCategorias());
        campoDescricao.sendKeys(produto.getDescricao());
        campoImagem.sendKeys(produto.getImagem());
        campoAtivo.click();

        botaoCadastro.click();
    }

    public File getRandomImageFile() {
        File resourcesDirectory = new File("src/test/resources/imagens_caneca/");

        File[] files = resourcesDirectory.listFiles();

        Random random = new Random();

        return Objects.requireNonNull(files)[random.nextInt(files.length - 1)];
    }
}
