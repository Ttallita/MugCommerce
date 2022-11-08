package selenium.scripts.PP_conducao;

import model.solicitacao.StatusSolicitacaoType;
import model.venda.StatusVendaType;
import org.junit.jupiter.api.Test;
import selenium.dataHelpers.VOs.ClienteVO;
import selenium.pageModels.components.HeaderAdmComponent;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.pageModels.dashboard.VendaAdmPage;
import selenium.pageModels.perfilCliente.PerfilClientePage;
import selenium.scripts.TesteAbstract;
import selenium.service.TesteCompraService;
import selenium.utils.UtilsTeste;
import utils.Utils;

import java.time.LocalDate;
import java.util.List;

public class PP_Cancelamento  extends TesteAbstract {

    private HeaderClienteComponent headerCliente;
    private HeaderAdmComponent headerAdm;

    @Override
    protected void configurarCenarioTeste() { }

    @Test
    public void testeRealizaTroca() throws InterruptedException {
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

        // trocar primeiro produto do modal
        UtilsTeste.getBotaoByValueInput("Solicitar troca", driver).click();

        List<String> identificadoresTrocaCliente = List.of(dataCompra, StatusSolicitacaoType.SOLICITADA.getNomeExibicao());

        // verifica se a troca existe na tela
        perfilCliente.getSideBarCliente().acessarTrocas().abrirModalTrocas(identificadoresTrocaCliente);
    }

}
