package selenium.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
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

    public static WebElement findLinhaTabela(WebDriver driver, List<String> identificadoresLinha){
        search:
        for (WebElement linha: driver.findElements(By.cssSelector("tbody > tr"))){

            for (String id : identificadoresLinha){
                if (!linha.getText().contains(id))
                    continue search;
            }

            return linha;
        }

        throw new NoSuchElementException(String.format("Linha com os identificadores [%s] não foi encontrada na tabela!", identificadoresLinha));
    }

    public static void esperarTelaRecarregar(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.tagName("html"))));
        esperarJavaScriptExecutar(wait);
    }

}
