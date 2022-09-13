package selenium.pageModels.perfilCliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.dataHelpers.VOs.EnderecoVO;
import selenium.pageModels.formularios.FormEnderecoPage;
import selenium.utils.UtilsTeste;

import java.util.List;

public class EnderecosPage extends PerfilPage {

    private List<WebElement> enderecos;

    public static final String TITULO_PAGINA = "Perfil - Endereços";

    public static final String LINK_ADICIONAR_ENDERECO = "/emug/formularios/formEndereco.jsp?operacao=salvar";

    public EnderecosPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public FormEnderecoPage adicionarEndereco(){
        UtilsTeste.getBotaoByLink(LINK_ADICIONAR_ENDERECO, driver).click();

        return new FormEnderecoPage(driver);
    }

    public void removerEndereco(EnderecoVO enderecoVO){
        WebElement botaoDeletarEndereco = null;

        for(WebElement end: driver.findElements(By.tagName("tr"))){
            String textoEndereco = end.getText();

            if(textoEndereco.contains(enderecoVO.getNumeroEndereco()) && textoEndereco.contains(enderecoVO.getCep())) {
                botaoDeletarEndereco = end.findElement(By.name("botaoModalDeletar"));
                break;
            }
        }

        botaoDeletarEndereco.click();

        driver.findElement(By.id("botaoRemoverEndereco")).click();
    }
}
