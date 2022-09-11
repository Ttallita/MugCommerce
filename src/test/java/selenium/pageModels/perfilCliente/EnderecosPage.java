package selenium.pageModels.perfilCliente;

import org.openqa.selenium.WebDriver;
import selenium.pageModels.formularios.FormEnderecoPage;
import selenium.utils.UtilsTeste;

public class EnderecosPage extends PerfilPage {

    public static final String TITULO_PAGINA = "Perfil - Endereços";

    public static final String LINK_ADICIONAR_ENDERECO = "/emug/formularios/formEndereco.jsp?operacao=salvar";

    public EnderecosPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public FormEnderecoPage adicionarEndereco(){
        UtilsTeste.getBotaoByLink(LINK_ADICIONAR_ENDERECO, driver).click();

        return new FormEnderecoPage(driver);
    }
}
