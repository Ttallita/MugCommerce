package selenium.pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.pageModels.components.HeaderAdmComponent;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.pageModels.components.HeaderComponentAbstract;
import selenium.pageModels.perfilCliente.PerfilPrincipalPage;
import utils.UtilsWeb;

import java.util.List;

public class HomePage extends PageAbstract{

    private HeaderComponentAbstract header;

    public static final String TITULO_PAGINA = "Home";

    public HomePage(WebDriver driver){
        super(driver, TITULO_PAGINA);

        header = getHeader(driver);
    }

    public PerfilPrincipalPage acessarPerfilCliente(){

        try {
             header.acessarPagina("perfil");
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalStateException("Esta não é a Home de cliente!");
        }

        return new PerfilPrincipalPage(driver);
    }

    public ProdutoPage abrirPaginaProduto(String nomeProduto){
        return ProdutoPage.abrirPaginaProduto(nomeProduto);
    }

    public ResultadoPesquisaPage pesquisar(String termoPesquisa){
        return header.pesquisar(termoPesquisa);
    }

}
