package selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UtilsTeste {

    public static WebElement getBotaoByLink(String link, WebDriver driver){
        return driver.findElement(By.cssSelector("a[href*='" + link + "']"));
    }

    public static WebElement getBotaoByValueInput(String valueInput, WebDriver driver){
        return driver.findElement(By.cssSelector("input[value*='" + valueInput + "']"));
    }

    public static String getMensagemAlert(WebDriver driver){
        return driver.findElement(By.className("alert")).findElement(By.tagName("li")).getText();
    }

}
