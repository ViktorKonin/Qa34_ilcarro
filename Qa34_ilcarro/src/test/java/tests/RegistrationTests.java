package tests;

import manager.MyDataProvider;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        //if logget ->> true - logout
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOk();
    }

    @Test(dataProvider = "dataRegistration", dataProviderClass = MyDataProvider.class)
    public void registrationSuccess(User user) {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        User user = new User().setName("List").setLastName("Snows").setEmail("lis" + i + "@gmail.com").setPassword("Ff12345$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        // app.getHelperUser().checkPolicy();
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMassage(), "Registered");


    }
    @Test
    public void RegistrationWrongPasswordFormat(){
        User user = new User().setName("Zoa").setLastName("DSnow").setEmail("zoa@gmail.com").setPassword("Zoa");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        Assert.assertTrue(app.getHelperUser().isErrorPasswordFormatDisplayed());
        Assert.assertTrue(app.getHelperUser().isErrorPasswordSizeDisplayed());
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());


    }
}
