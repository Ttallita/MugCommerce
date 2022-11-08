package selenium.pageModels.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ModalCancelamentosComponent extends ModalAbstract {

    public ModalCancelamentosComponent(WebDriver driver) {
        super(driver);
    }

    public void fecharModal() throws InterruptedException {
        Thread.sleep(1000L);
        driver.findElement(By.className("btn-close")).click();
    }
}