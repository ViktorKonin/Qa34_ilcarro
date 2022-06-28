package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void successLogin() {

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("viktor@gmail.com", "Vviktor12345$");
        app.getHelperUser().submitLogin();

        //6.Assert (is login success?) logout present?

    }


    @Test
    public void loginNegativeTestsWrongEmail() {

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("viktorgmail.com", "Vviktor12345$");
        app.getHelperUser().submitLogin();
        //6.Assert (is login success?) logout present? NOT

    }

}
