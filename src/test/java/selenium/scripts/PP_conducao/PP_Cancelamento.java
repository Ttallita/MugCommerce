package selenium.scripts.PP_conducao;

import model.solicitacao.StatusSolicitacaoType;
import model.venda.StatusVendaType;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.dataHelpers.VOs.ClienteVO;
import selenium.pageModels.components.HeaderAdmComponent;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.pageModels.dashboard.VendaAdmPage;
import selenium.pageModels.perfilCliente.PerfilClientePage;
import selenium.scripts.TesteAbstract;
import selenium.service.TesteCompraService;
import utils.Utils;

import java.time.LocalDate;
import java.util.List;

public class PP_Cancelamento  extends TesteAbstract {

    private HeaderClienteComponent headerCliente;
    private HeaderAdmComponent headerAdm;

    @Override
    protected void configurarCenarioTeste() { }

    @Test
    public void testeRealizaCancelamento() throws InterruptedException {
        ClienteVO cliente = ClienteVO.createClienteVOPadrao();

        headerCliente = (HeaderClienteComponent) this.realizarLoginClientePadrao().getHeader(driver);

        TesteCompraService.realizarCompra(driver, headerCliente);
        String dataCompra = Utils.formataLocalDateBR(LocalDate.now());

        headerCliente.deslogar();

        headerAdm = (HeaderAdmComponent) super.realizarLoginAdmPadrao().getHeader(driver);

        VendaAdmPage vendaPage = headerAdm.acessarDashboard().getSideBarAdm().acessarVendas();

        List<String> identificadoresCompraAdm = List.of(dataCompra, cliente.getNome());
        vendaPage.abrirDetalhesCompra(identificadoresCompraAdm).alterarStatus(StatusVendaType.PAGAMENTO_REALIZADO.nomeExibicao);
        vendaPage.abrirDetalhesCompra(identificadoresCompraAdm).alterarStatus(StatusVendaType.EM_TRANSPORTE.nomeExibicao);
        vendaPage.abrirDetalhesCompra(identificadoresCompraAdm).alterarStatus(StatusVendaType.ENTREGA_REALIZADA.nomeExibicao);

        headerAdm.deslogar();

        headerCliente = (HeaderClienteComponent) this.realizarLoginClientePadrao().getHeader(driver);
        PerfilClientePage perfilCliente = headerCliente.acessarPerfil();

        List<String> identificadoresCompraCliente = List.of(dataCompra, StatusVendaType.ENTREGA_REALIZADA.nomeExibicao);
        perfilCliente.getSideBarCliente().acessarCompras().abrirModalCompra(identificadoresCompraCliente);

        WebElement botaoCancelarCompra = driver.findElement(By.id("botaoCancelarCompra"));
        botaoCancelarCompra.click();

        List<String> identificadores = List.of(dataCompra, StatusSolicitacaoType.SOLICITADA.getNomeExibicao());

        // verifica se a troca existe na tela
        perfilCliente.getSideBarCliente().acessarCancelamentos().abrirModalCancelamentos(identificadores);
        Thread.sleep(2000);
    }

}
