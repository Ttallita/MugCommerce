package selenium.pageModels.perfilCliente;

import org.openqa.selenium.WebDriver;
import selenium.pageModels.PageAbstract;
import selenium.pageModels.components.SideBarClienteComponent;

public abstract class PerfilPage extends PageAbstract {

    protected SideBarClienteComponent sideBarCliente;

    public PerfilPage(WebDriver driver, String tituloPagina) {
        super(driver, tituloPagina);

        sideBarCliente = new SideBarClienteComponent(driver);
    }

    public PerfilPrincipalPage acessarPerfil(){
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
