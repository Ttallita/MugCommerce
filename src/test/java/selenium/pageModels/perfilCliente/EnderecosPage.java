package selenium.pageModels.perfilCliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.dataHelpers.VOs.CartaoVO;
import selenium.dataHelpers.VOs.EnderecoVO;
import selenium.pageModels.PageAbstract;
import selenium.pageModels.formularios.FormEnderecoPage;
import selenium.utils.UtilsTeste;

import java.util.List;

public class EnderecosPage extends PageAbstract {

    private List<WebElement> enderecos;

    public static final String TITULO_PAGINA = "Perfil - Endereços";

    public static final String LINK_ADICIONAR_ENDERECO = "/emug/cliente/formularios/formEndereco.jsp";
    public static final String LINK_EDITAR_ENDERECO = "/emug/clientes/enderecos?operacao=listarUnico";

    public EnderecosPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public FormEnderecoPage adicionarEndereco(){
        UtilsTeste.getBotaoByLink(LINK_ADICIONAR_ENDERECO, driver).click();

        return new FormEnderecoPage(driver);
    }

    public FormEnderecoPage editarEndereco(EnderecoVO enderecoVO){

        WebElement trEnderecoEditar = driver.findElements(By.tagName("tr"))
                .stream()
                .filter(tr -> tr.getText().contains(enderecoVO.getCep()))
                .findFirst()
                .orElse(null);

        if(trEnderecoEditar == null)
            throw new RuntimeException("Erro ao encontrar endereço na tabela");

        WebElement botaoEditar = trEnderecoEditar.findElement(By.cssSelector("a[href*='" + LINK_EDITAR_ENDERECO + "']"));

        if(botaoEditar == null)
            throw new RuntimeException("Não existem endereços para serem editados!");

        botaoEditar.click();

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

        if(botaoDeletarEndereco == null)
            throw new RuntimeException("Não existem cartões para serem deletados!");

        botaoDeletarEndereco.click();

        driver.findElement(By.id("botaoRemoverEndereco")).click();
    }
}
