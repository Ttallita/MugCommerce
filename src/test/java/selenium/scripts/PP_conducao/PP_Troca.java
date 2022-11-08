package selenium.scripts.PP_conducao;

import org.junit.jupiter.api.Test;
import selenium.dataHelpers.VOs.ClienteVO;
import selenium.pageModels.components.HeaderAdmComponent;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.scripts.TesteAbstract;
import selenium.pageModels.dashboard.VendaAdmPage;
import selenium.service.TesteCompraService;
import utils.Utils;

import java.time.LocalDate;
import java.util.List;

public class PP_Troca extends TesteAbstract {

    private HeaderClienteComponent headerCliente;
    private HeaderAdmComponent headerAdm;

    @Override
    protected void configurarCenarioTeste() {

    }

    @Test
    public void testeRealizaTroca() throws InterruptedException {
        ClienteVO cliente = ClienteVO.createClienteVOPadrao();

        headerCliente = (HeaderClienteComponent) this.realizarLoginClientePadrao().getHeader(driver);

        TesteCompraService.realizarCompra(driver, headerCliente);
        String dataCompra = Utils.formataLocalDateBR(LocalDate.now());

//        SideBarClienteComponent sideBarCliente = headerCliente.acessarPerfil().getSideBarCliente();
//        sideBarCliente.acessarCompras().abrirModalCompra(List.of(dataCompra, "Em processamento"));
        headerCliente.deslogar();

        headerAdm = (HeaderAdmComponent) super.realizarLoginAdmPadrao().getHeader(driver);

        VendaAdmPage vendaPage = headerAdm.acessarDashboard().getSideBarAdm().acessarVendas();

        List<String> identificadoresCompra = List.of(dataCompra, cliente.getNome());
        vendaPage.abrirDetalhesCompra(identificadoresCompra).alterarStatus("Pagamento realizado");
        vendaPage.abrirDetalhesCompra(identificadoresCompra).alterarStatus("Em transporte");
        vendaPage.abrirDetalhesCompra(identificadoresCompra).alterarStatus("Entrega realizada");

    }
}
