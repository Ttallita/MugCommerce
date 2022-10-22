package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.dataHelpers.VOs.CartaoVO;

import java.time.Duration;
import java.util.NoSuchElementException;

public abstract class ModalAbstract {

    protected WebDriver driver;

    public ModalAbstract(WebDriver driver){
        this.driver = driver;
    }

    public void alterarItensSelecionados(){
        driver.findElement(By.id("botaoAlterarModal")).click();
    }

    public boolean isItemListado(String identificadorItem){
        try {
            getItemModal(identificadorItem);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    protected WebElement getItemModal(String nomeItem) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("botaoAdicionarModal")));

        for(WebElement i : driver.findElements(By.className("form-check"))){
            if (i.findElement(By.tagName("label")).getText().contains(nomeItem))
                return i;
        }

        throw new NoSuchElementException("Item não encontrado no modal");
    }

}
