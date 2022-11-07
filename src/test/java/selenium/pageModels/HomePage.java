package selenium.pageModels;

import org.openqa.selenium.WebDriver;
import selenium.pageModels.components.HeaderComponentAbstract;
import selenium.pageModels.perfilCliente.PerfilClientePage;

public class HomePage extends PageAbstract{

    private HeaderComponentAbstract header;

    public static final String TITULO_PAGINA = "Home";

    public HomePage(WebDriver driver){
        super(driver, TITULO_PAGINA);

        header = getHeader(driver);
    }

    public PerfilClientePage acessarPerfilCliente(){

        try {
            PerfilClientePage perfil = header.acessarPagina("perfil");
            return perfil;
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalStateException("Esta não é a Home de cliente!");
        }
    }

    public ProdutoPage abrirPaginaProduto(String nomeProduto){
        return ProdutoPage.abrirPaginaProduto(nomeProduto);
    }

    public ResultadoPesquisaPage pesquisar(String termoPesquisa){
        return header.pesquisar(termoPesquisa);
    }

}
