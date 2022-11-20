package selenium.scripts.PP_conducao;

import org.junit.jupiter.api.Test;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.scripts.TesteAbstract;
import selenium.service.TesteCompraService;

public class PP_Compra extends TesteAbstract {

    @Override
    protected void configurarCenarioTeste() throws InterruptedException {}
    private HeaderClienteComponent headerCliente;

    @Test
    public void realizaCompra() throws InterruptedException {
        headerCliente = (HeaderClienteComponent) this.realizarLoginClientePadrao().getHeader(driver);

        for(int i = 0; i < 499; i++) {
            TesteCompraService.realizarCompra(driver, headerCliente);
        }
    }


}
