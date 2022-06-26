package tests;

import org.testng.annotations.Test;

public class LoginTests extends TestBase {


    //success login
    @Test
    public void successLogin() {
        //3.open form
        openLoginForm();

        //4.fill form + valid data
        fillLoginForm("viktor@gmail.com", "Vviktor12345$");

        //5.submit login
        submitLogin();

        //6.Assert (is login success?) logout present?

    }


    // login negative
    @Test
    public void loginNegativeTestsWrongEmail() {

        //3.open form
        openLoginForm();

        //4.fill form + invalid data
        fillLoginForm("viktorgmail.com", "Vviktor12345$");

        //5.submit login
        submitLogin();

        //6.Assert (is login success?) logout present? NOT

    }

}
