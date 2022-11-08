package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.pageModels.ProdutoPage;
import selenium.pageModels.dashboard.ProdutoAdmPage;
import selenium.pageModels.dashboard.VendaAdmPage;

public class SideBarAdmComponent {
    private final WebElement paginaProdutos;
    private final WebElement paginaVendas;


    private final WebDriver driver;

    public SideBarAdmComponent(WebDriver driver) {
        paginaProdutos = driver.findElement(By.id("paginaProdutos"));
        paginaVendas = driver.findElement(By.id("paginaVendas"));
        this.driver = driver;
    }

    public ProdutoAdmPage acessarProdutos() {
        paginaProdutos.click();
        return new ProdutoAdmPage(driver);
    }

    public VendaAdmPage acessarVendas() {
        paginaVendas.click();
        return new VendaAdmPage(driver);
    }
}

