package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class HelperBase {
    WebDriver wd;



    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    //find, click, clear, sendKeys
    public void type(By locator, String text) {
        if (text != null) {
            WebElement element = wd.findElement(locator);
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }

    public void submit() {
        new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        wd.findElement(By.xpath("//button[@type='submit']")).click();

    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public boolean isElementPresent(By locator) {

        return wd.findElements(locator).size() > 0;
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMassage() {
        //pause
        pause(2000);
        //wait container
        new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("div.dialog-container"))));
        //get text
        return wd.findElement(By.cssSelector("div.dialog-container h1")).getText();
    }

    public void jsexample() {
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#name').value='lola';");
        js.executeScript("document.querySelector('#terms-of-use').checked=true;");
    }

    public void takeScreenShots(String pathToFile) {
        File tmp = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        File screen = new File(pathToFile);
        try {
            Files.copy(tmp, screen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void returnToHome() {
        click(By.cssSelector(".logo"));
    }
}
