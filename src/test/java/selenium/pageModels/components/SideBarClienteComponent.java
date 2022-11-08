package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.pageModels.*;
import selenium.pageModels.perfilCliente.*;

public class SideBarClienteComponent {

    protected WebDriver driver;

    public SideBarClienteComponent(WebDriver driver){
        this.driver = driver;
    }

    public PerfilClientePage acessarPerfil(){
        driver.findElement(By.id("paginaPerfil")).click();
        return new PerfilClientePage(driver);
    }

    public AtualizarSenhaPage acessarAtualizarSenha(){
        driver.findElement(By.id("paginaAtualizarSenha")).click();
        return new AtualizarSenhaPage(driver);
    }

    public ComprasPage acessarCompras(){
        driver.findElement(By.id("paginaCompras")).click();
        return new ComprasPage(driver);
    }

    public EnderecosPage acessarEnderecos(){
        driver.findElement(By.id("paginaEnderecos")).click();
        return new EnderecosPage(driver);
    }

    public CartoesPage acessarCartoes(){
        driver.findElement(By.id("paginaCartoes")).click();
        return new CartoesPage(driver);
    }

    public CuponsPage acessarCupons(){
        driver.findElement(By.id("paginaCupons")).click();
        return new CuponsPage(driver);
    }

    public CancelamentosPage acessarCancelamentos(){
        driver.findElement(By.id("paginaCancelamentos")).click();
        return new CancelamentosPage(driver);
    }

    public TrocasPage acessarTrocas(){
        driver.findElement(By.id("paginaTrocas")).click();
        return new TrocasPage(driver);
    }

}
