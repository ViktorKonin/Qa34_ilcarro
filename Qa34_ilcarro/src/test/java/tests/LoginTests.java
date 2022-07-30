package tests;

import manager.MyDataProvider;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Test start with logout");
        }
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOk();
    }

    @Test(dataProvider = "dataLogin",dataProviderClass = MyDataProvider.class)
    public void successLoginDP(String email,String password) {
        logger.info("Test start with email: " + email + "& password: " + password);
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("viktor9@gmail.com", "Vviktor12345$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMassage(),"Logged in");
        logger.info("Test passed");
    }

    @Test(dataProvider = "loginCSV",dataProviderClass = MyDataProvider.class)
    public void successLoginDpCSV(User user) {
        logger.info("Test start with email: " + user.getEmail() + "& password: " + user.getPassword());
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMassage(),"Logged in");
        logger.info("Test passed");
    }


    @Test
    public void loginNegativeTestsWrongEmail() {

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("viktorgmail.com", "Vviktor12345$");
        //app.getHelperUser().submit();
        //6.Assert (is login success?) logout present? NOT
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertTrue(app.getHelperUser().isErrorEmailFormatDisplayed());
    }


}
