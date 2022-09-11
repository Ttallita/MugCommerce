package selenium.pageModels.formularios;

import model.cliente.endereco.Endereco;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.dataHelpers.VOs.EnderecoVO;
import selenium.pageModels.PageAbstract;
import selenium.pageModels.components.FormEnderecoComponent;

public class FormEnderecoPage extends PageAbstract {

    private final FormEnderecoComponent formEndereco;
    private final WebElement botaoCadastro;

    public static final String TITULO_PAGINA = "Cadastro endereço";

    public FormEnderecoPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);

        formEndereco = new FormEnderecoComponent(driver);
        botaoCadastro = driver.findElement(By.name("botaoCadastro"));
    }

    public FormEnderecoPage salvarNovoEndereco(EnderecoVO endereco){
        formEndereco.salvarNovoEndereco(endereco);
        return this;
    }

}
