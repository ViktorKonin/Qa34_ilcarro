package tests;

import manager.MyDataProvider;
import models.Car;
import models.User;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCar extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("viktor9@gmail.com").setPassword("Vviktor12345$"));
        }


    }

    @Test(groups = {"web", "smoke", "regress"},enabled = false)
    public void addNewCarSuccess() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        Car car = Car.builder()
                .address("Haifa, Israel")
                .make("BMW")
                .model("M5")
                .year("2021")
                .engine("2.5")
                .fuel("Petrol")
                .gear("AT")
                .wD("AWD")
                .doors("5")
                .seats("4")
                .clasS("C")
                .fuelConsumption("6.5")
                .carRegNumber("222-333-" + i)
                .price("65")
                .distanceIncluded("800")
                .features("type of features")
                .about("very nice car")
                .build();
        app.car().openCarForm();
        app.car().fillCarForm(car);
        app.car().attachPhoto("C:\\Automation_QA34\\Qa34_ilcarro\\auto1.jpeg");
        app.car().submit();
        Assert.assertEquals(app.getHelperUser().getMassage(), "Car added");
    }

    @Test(dataProvider = "validDataCar", dataProviderClass = MyDataProvider.class, enabled = false)
    public void addNewCarSuccess2(Car car) {

        app.car().openCarForm();
        app.car().fillCarForm(car);
        app.car().attachPhoto("C:\\Automation_QA34\\Qa34_ilcarro\\auto1.jpeg");
        app.car().submit();
        Assert.assertEquals(app.getHelperUser().getMassage(), "Car added");
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        app.car().pageUp();
        app.car().returnToHome();
    }

}
