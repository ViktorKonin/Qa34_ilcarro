package tests;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SearchTests extends TestBase {

    @Test
    public void searchCurrentMonth() {
        app.search().searchCurrentMonth("Tel Aviv", "7/28/2022", "7/27/2022");
        app.search().submit();
        Assert.assertTrue(app.search().isListOfCarsAppeared());

    }
    @Test
    public void searchNextMonth() {
        app.search().searchNextMonth("Tel Aviv", "8/28/2022", "8/27/2022");
        app.search().submit();
        Assert.assertTrue(app.search().isListOfCarsAppeared());

    }


    @Test
    public void searchPeriodPast(){

    }

    @Test
    public void searchCurrentYear() {
        app.search().searchCurrentYear("Haifa", "8/10/2022", "8/20/2022");
        app.search().submit();
        Assert.assertTrue(app.search().isListOfCarsAppeared());
        Assert.assertTrue(app.search().isPageResultAppeared());
    }
    @Test
    public void searchCurrentYear2() {
        app.search().searchCurrentYear("Haifa", "8/10/2022", "10/20/2022");
        app.search().submit();
        Assert.assertTrue(app.search().isListOfCarsAppeared());
        Assert.assertTrue(app.search().isPageResultAppeared());
    }

    @Test
    public void searchCurrentYearLocalDate(){
        app.search().searchCurrentYearLocalDate("Haifa", "8/10/2022", "10/20/2022");
        app.search().submit();
        Assert.assertTrue(app.search().isListOfCarsAppeared());
    }
    @Test
    public void searchAnyPeriod(){
        app.search().searchAnyPeriodLocalDate2("Haifa", "6/10/2023", "7/15/2023");
        app.search().submit();
        Assert.assertTrue(app.search().isListOfCarsAppeared());
    }


    @AfterMethod
    public void returnToHome(){
        app.search().returnToHome();
    }
}