package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.pageModels.*;
import selenium.pageModels.perfilCliente.*;

public class SideBarClienteComponent {

    private final WebElement paginaPerfil;
    private final WebElement paginaAtualizarSenha;
    private final WebElement paginaCompras;
    private final WebElement paginaEnderecos;
    private final WebElement paginaCartoes;
    private final WebElement paginaCupons;
    private final WebElement paginaTrocas;
    private final WebElement paginaCancelamentos;

    protected WebDriver driver;

    public SideBarClienteComponent(WebDriver driver){
        paginaPerfil = driver.findElement(By.id("paginaPerfil"));
        paginaAtualizarSenha = driver.findElement(By.id("paginaAtualizarSenha"));
        paginaCompras = driver.findElement(By.id("paginaCompras"));
        paginaEnderecos = driver.findElement(By.id("paginaEnderecos"));
        paginaCartoes = driver.findElement(By.id("paginaCartoes"));
        paginaCupons = driver.findElement(By.id("paginaCupons"));
        paginaTrocas = driver.findElement(By.id("paginaTrocas"));
        paginaCancelamentos = driver.findElement(By.id("paginaCancelamentos"));

        this.driver = driver;
    }

    public PerfilClientePage acessarPerfil(){
        paginaPerfil.click();
        return new PerfilClientePage(driver);
    }

    public AtualizarSenhaPage acessarAtualizarSenha(){
        paginaAtualizarSenha.click();
        return new AtualizarSenhaPage(driver);
    }

    public ComprasPage acessarCompras(){
        paginaCompras.click();
        return new ComprasPage(driver);
    }

    public EnderecosPage acessarEnderecos(){
        paginaEnderecos.click();
        return new EnderecosPage(driver);
    }

    public CartoesPage acessarCartoes(){
        paginaCartoes.click();
        return new CartoesPage(driver);
    }

    public CuponsPage acessarCupons(){
        paginaCupons.click();
        return new CuponsPage(driver);
    }

    public CancelamentosPage acessarCancelamentos(){
        paginaCancelamentos.click();
        return new CancelamentosPage(driver);
    }

    public TrocasPage acessarTrocas(){
        paginaTrocas.click();
        return new TrocasPage(driver);
    }

}
