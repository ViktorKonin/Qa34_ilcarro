package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {
    @BeforeTest
    public void preCondition() {
        //if logget ->> true - logout
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        User user = new User().setName("List").setLastName("Snows").setEmail("lis" + i + "@gmail.com").setPassword("Ff12345$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        // app.getHelperUser().checkPolicy();
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getMassage(), "Registered");
        app.getHelperUser().clickOk();

    }
}
