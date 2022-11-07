package selenium.pageModels.perfilCliente;

import org.openqa.selenium.WebDriver;
import selenium.pageModels.PageAbstract;
import selenium.pageModels.components.SideBarClienteComponent;

public class PerfilClientePage extends PageAbstract {

    protected SideBarClienteComponent sideBarCliente;

    public static final String TITULO_PAGINA = "Perfil - Principal";


    public PerfilClientePage(WebDriver driver) {
        super(driver, TITULO_PAGINA);

        sideBarCliente = new SideBarClienteComponent(driver);
    }

    public PerfilClientePage acessarPerfil(){
        return sideBarCliente.acessarPerfil();
    }

    public AtualizarSenhaPage acessarAtualizarSenha(){
        return sideBarCliente.acessarAtualizarSenha();
    }

    public ComprasPage acessarCompras(){
        return sideBarCliente.acessarCompras();
    }

    public EnderecosPage acessarEnderecos(){
        return sideBarCliente.acessarEnderecos();
    }

    public CartoesPage acessarCartoes(){
        return sideBarCliente.acessarCartoes();
    }

    public CuponsPage acessarCupons(){
        return sideBarCliente.acessarCupons();
    }

}
