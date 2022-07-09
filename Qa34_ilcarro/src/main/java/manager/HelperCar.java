package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HelperCar extends HelperBase{
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
    }

    public void fillCarForm(Car car){
        typeAddress(car.getAddress());


        select(By.id("fuel"),car.getFuel());

    }
    private void select(By locator, String option){
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
        //select.selectByIndex(1);
        //select.deselectByVisibleText(option);

    }

    private void typeAddress(String address) {
        type(By.id("pickUpPlace"),address);
        //click on first address
        click(By.cssSelector("div.pac-item"));
        pause(500);
    }

    public void attachPhoto(String link) {

    }
}
