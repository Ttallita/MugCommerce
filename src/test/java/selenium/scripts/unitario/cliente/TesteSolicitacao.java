package selenium.scripts.unitario.cliente;

import org.junit.jupiter.api.Test;
import selenium.pageModels.components.HeaderClienteComponent;
import selenium.scripts.unitario.TesteUnitarioAbstract;

public class TesteSolicitacao extends TesteUnitarioAbstract {

    private HeaderClienteComponent headerCliente;

    @Override
    protected void configurarCenarioTeste() throws InterruptedException {
        headerCliente = (HeaderClienteComponent) this.realizarLoginClientePadrao().getHeader(driver);
        super.realizarCompra(headerCliente);
    }

    @Test
    public void teste(){

    }


}
