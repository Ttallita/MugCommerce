package selenium.pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderComponent {

    private boolean isHeaderCliente;

    private WebDriver driver;

    public HeaderComponent(WebDriver driver){
        this.driver = driver;

        String tipoHeader = driver.findElement(By.tagName("header")).getAttribute("name");
        isHeaderCliente = tipoHeader.equals("CLIENTE");
    }

    public boolean isHeaderCliente() {
        return isHeaderCliente;
    }
}
