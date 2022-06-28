package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperUser extends HelperBase {

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        WebElement loginTab = wd.findElement(By.xpath("//*[@href='/login?url=%2Fsearch']"));
        //WebElement loginTab = wd.findElement(By.xpath("//a[text()=' Log in ']"));
        loginTab.click();

    }

    public void fillLoginForm(String email, String password) {
        type(By.xpath("//input[@id='email']"), email);
        type(By.xpath("//input[@id='password']"), password);
    }

    public void submitLogin() {
        wd.findElement(By.xpath("//button[@type='submit']")).click();

    }

    public boolean isLogged() {
        List<WebElement> list = wd.findElements(By.xpath("//button[text()='Ok']"));
        return list.size()>0;
    }

    public void logout() {
        click(By.xpath("//button[text()='Ok']"));
        click(By.xpath("//*[text()=' Logout ']"));
    }
}
