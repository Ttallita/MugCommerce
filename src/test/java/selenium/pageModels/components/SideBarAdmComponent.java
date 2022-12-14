package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.pageModels.ProdutoPage;
import selenium.pageModels.dashboard.CancelamentosAdmPage;
import selenium.pageModels.dashboard.ProdutoAdmPage;
import selenium.pageModels.dashboard.TrocasAdmPage;
import selenium.pageModels.dashboard.VendaAdmPage;

public class SideBarAdmComponent {

    private final WebDriver driver;

    public SideBarAdmComponent(WebDriver driver) {
        this.driver = driver;
    }

    public ProdutoAdmPage acessarProdutos() {
        driver.findElement(By.id("paginaProdutos")).click();
        return new ProdutoAdmPage(driver);
    }

    public VendaAdmPage acessarVendas() {
        driver.findElement(By.id("paginaVendas")).click();
        return new VendaAdmPage(driver);
    }

    public TrocasAdmPage acessarTrocas() {
        driver.findElement(By.id("paginaTrocas")).click();
        return new TrocasAdmPage(driver);
    }

    public CancelamentosAdmPage acessarCancelamentos() {
        driver.findElement(By.id("paginaCancelamentos")).click();
        return new CancelamentosAdmPage(driver);
    }
}

