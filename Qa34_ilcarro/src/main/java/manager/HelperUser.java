package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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



    public boolean isLogged() {
        List<WebElement> list = wd.findElements(By.xpath("//button[text()='Ok']"));
        return list.size()>0;
    }

    public void logout() {
        click(By.xpath("//button[text()='Ok']"));
        click(By.xpath("//*[text()=' Logout ']"));
    }

    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"),user.getName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy() {
       click(By.cssSelector("label[for='terms-of-use']"));

    }
    public void checkPolicyXY() {
        WebElement lable = wd.findElement(By.cssSelector("label[for='terms-of-use']"));
        Rectangle rect = lable.getRect();
        int yOffSet = rect.getHeight()/2;
        int xOffSet = rect.getWidth()/2;

        Actions actions = new Actions(wd);
        actions.moveToElement(lable).release().perform();
        actions.moveByOffset(-xOffSet,-yOffSet).click().release().perform();

    }

    public void clickOk() {
        click(By.xpath("//button[text()='Ok']"));
    }
}
