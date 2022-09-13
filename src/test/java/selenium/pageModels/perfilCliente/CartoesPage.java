package selenium.pageModels.perfilCliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.dataHelpers.VOs.CartaoVO;
import selenium.dataHelpers.VOs.EnderecoVO;
import selenium.pageModels.formularios.FormCartaoPage;
import selenium.utils.UtilsTeste;

public class CartoesPage extends PerfilPage {

    public static final String TITULO_PAGINA = "Perfil - Cartões";

    public static final String LINK_ADICIONAR_CARTAO = "/emug/formularios/formCartaoCredito.jsp";

    public CartoesPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public FormCartaoPage adicionarCartao(){
        UtilsTeste.getBotaoByLink(LINK_ADICIONAR_CARTAO, driver).click();

        return new FormCartaoPage(driver);
    }

    public void removerCartao(CartaoVO cartaoVO){
        WebElement botaoDeletarCartao = null;

        for(WebElement cartao: driver.findElements(By.tagName("tr"))){
            String textoCartao = cartao.getText();

            if(textoCartao.contains(cartaoVO.getCodigoCartao()) && textoCartao.contains(cartaoVO.getCodigoCartao())) {
                botaoDeletarCartao = cartao.findElement(By.name("botaoModalDeletar"));
                break;
            }
        }

        if(botaoDeletarCartao == null)
            throw new RuntimeException("Não existem cartões para serem deletados!");

        botaoDeletarCartao.click();

        driver.findElement(By.id("botaoRemoverCartao")).click();
    }

}
