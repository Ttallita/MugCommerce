package selenium.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

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

    public static void esperarJavaScriptExecutar(WebDriverWait wait) {
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) Objects.requireNonNull(wd)).executeScript("return document.readyState").equals("complete"));
    }

    public static WebElement findElementLinhaTabela(WebDriver driver, String textoIdentificador){
        WebElement linhaTabela = null;

        for (WebElement tr:  driver.findElements(By.cssSelector("tbody > tr"))){
            linhaTabela = tr.findElements(By.tagName("td")).stream()
                    .filter(td -> td.getText().equals(textoIdentificador))
                    .findFirst().orElse(null);

            if (linhaTabela != null)
                break;
        }

        if (linhaTabela == null)
            throw new NoSuchElementException(String.format("Elemento com identificador %s não foi encontrado na tabela!", textoIdentificador));

        return linhaTabela;
    }

    public static void esperarTelaRecarregar(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.tagName("html"))));
        esperarJavaScriptExecutar(wait);
    }

}
