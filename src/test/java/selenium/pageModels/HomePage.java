package selenium.pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.pageModels.components.HeaderAdmComponent;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.pageModels.components.HeaderComponentAbstract;
import selenium.pageModels.perfilCliente.PerfilPrincipalPage;

public class HomePage extends PageAbstract{

    private HeaderComponentAbstract header;

    public static final String TITULO_PAGINA = "Home";

    public HomePage(WebDriver driver){
        super(driver, TITULO_PAGINA);

        header = getHeader(driver);
    }

    public PerfilPrincipalPage acessarPerfilCliente(){

        try {
            ((HeaderClienteComponent) header).acessarPerfil();
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalStateException("Esta não é a Home de cliente!");
        }

        return new PerfilPrincipalPage(driver);
    }

    private HeaderComponentAbstract getHeader(WebDriver driver) {
        String tipoHeader = driver.findElement(By.tagName("header")).getAttribute("name");

        if(tipoHeader != null && !tipoHeader.isEmpty())
            header = tipoHeader.equals("CLIENTE") ? new HeaderClienteComponent(driver) : new HeaderAdmComponent(driver);
        else
            header = new HeaderComponentAbstract(driver);

        return header;
    }

    public HeaderComponentAbstract getHeader() {
        return header;
    }

    public void setHeader(HeaderComponentAbstract header) {
        this.header = header;
    }

}
