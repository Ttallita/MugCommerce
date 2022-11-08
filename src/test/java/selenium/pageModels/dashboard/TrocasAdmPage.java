package selenium.pageModels.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.pageModels.PageAbstract;
import selenium.utils.UtilsTeste;

import java.util.List;

public class TrocasAdmPage extends PageAbstract {

    public static final String TITULO_PAGINA = "Gerenciar - Trocas";

    public TrocasAdmPage(WebDriver driver) {
        super(driver, TITULO_PAGINA);
    }

    public DetalhesSolicitacaoPage abrirDetalhesTroca(List<String> identificadoresCompra){
        UtilsTeste.findLinhaTabela(driver, identificadoresCompra)
                .findElement(By.tagName("a"))
                .click();

        return new DetalhesSolicitacaoPage(driver);
    }
}
