package selenium.scripts.PP_conducao;

import org.junit.jupiter.api.Test;
import selenium.pageModels.components.HeaderAdmComponent;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.pageModels.components.SideBarClienteComponent;
import selenium.scripts.TesteAbstract;
import selenium.service.TesteCompraService;
import utils.Utils;

import java.time.LocalDate;
import java.util.List;

public class PP_Troca extends TesteAbstract {

    private HeaderClienteComponent headerCliente;
    private HeaderAdmComponent headerAdm;

    @Override
    protected void configurarCenarioTeste() { }

    @Test
    public void testeRealizaTroca() throws InterruptedException {
        headerCliente = (HeaderClienteComponent) this.realizarLoginClientePadrao().getHeader(driver);
        TesteCompraService.realizarCompra(driver, headerCliente);
        headerCliente.deslogar();

        SideBarClienteComponent sideBarCliente = headerCliente.acessarPerfil().getSideBarCliente();
        sideBarCliente.acessarCompras().abrirModalCompra(List.of(Utils.formataLocalDateBR(LocalDate.now()), "Em processamento"));

        headerCliente.deslogar();


    }
}
