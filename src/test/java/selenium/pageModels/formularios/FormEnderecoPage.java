package selenium.pageModels.formularios;

import org.openqa.selenium.WebDriver;
import selenium.dataHelpers.VOs.EnderecoVO;
import selenium.pageModels.PageAbstract;
import selenium.pageModels.components.FormEnderecoComponent;

public class FormEnderecoPage extends PageAbstract {

    private final FormEnderecoComponent formEndereco;
    public static final String TITULO_PAGINA = "Cadastro endereço";

    public FormEnderecoPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);

        formEndereco = new FormEnderecoComponent(driver);
    }

    public FormEnderecoPage salvarNovoEndereco(EnderecoVO endereco){
        formEndereco.salvarNovoEndereco(endereco);
        return this;
    }

}
