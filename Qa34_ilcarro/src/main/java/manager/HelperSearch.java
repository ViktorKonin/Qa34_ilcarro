package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperSearch extends HelperBase {
    public HelperSearch(WebDriver wd) {
        super(wd);
    }


    public void searchCurrentMonth(String city, String dataFrom, String dataTo) {
        typeCity(city);
        clearPeriod();
        selectPeriodCurrentMonth(dataFrom, dataTo);
    }

    private void selectPeriodCurrentMonth(String dataFrom, String dataTo) {
        //     "7/25/2022"      "7/30/2022"
        clearPeriod();
        click(By.id("dates"));

        String[] from = dataFrom.split("/"); // ["7"],["25"],["2022"  from[1] = "25"

        String locator = "//div[text()=' " + from[1] + " ']";
        click(By.xpath(locator));


        //     "7/30/2022"
        String[] to = dataTo.split("/"); // ["7"],["30"],["2022"]     to[1]

        String locator2 = String.format("//div[text()=' %s ']", to[1]);

        click(By.xpath(locator2));

    }

    private void typeCity(String city) {

        type(By.id("city"), city);
        pause(500);
        click(By.cssSelector(".pac-item"));

    }

    public void searchCurrentYear(String city, String dataFrom, String dataTo) {
        typeCity(city);
        clearPeriod();
        selectPeriodCurrentYear(dataFrom, dataTo);

    }

    private void selectPeriodCurrentYear(String dataFrom, String dataTo) {
        //     "8/10/2022", "10/20/2022"
        String dataNow = "7/20/2022";
        String[] now = dataNow.split("/");
        String[] from = dataFrom.split("/");
        String[] to = dataTo.split("/");
        clearPeriod();
        click(By.id("dates"));

        if (!now[0].equals(from[0])) {
            int count = Integer.parseInt(from[0]) - Integer.parseInt(now[0]);
            clickByNextMonth(count);
        }
        String locator = String.format("//div[text()=' %s ']", from[1]);
        click(By.xpath(locator));

        if (Integer.parseInt(from[0]) != Integer.parseInt(to[0])) {
            int count = Integer.parseInt(to[0]) - Integer.parseInt(from[0]);
            clickByNextMonth(count);
        }

        String locator1 = String.format("//div[text()=' %s ']", to[1]);
        click(By.xpath(locator1));

    }


    public void searchCurrentYearLocalDate(String city, String dataFrom, String dataTo) {
        typeCity(city);
        clearPeriod();
        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dataFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dataTo, DateTimeFormatter.ofPattern("M/d/yyyy"));
        click(By.id("dates"));
        if (now.getMonthValue() != from.getMonthValue()) {
            int count = from.getMonthValue() - now.getMonthValue();
            clickByNextMonth(count);
        }
        String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locator));

        if (from.getMonthValue() != to.getMonthValue()) {
            int count = to.getMonthValue() - from.getMonthValue();
            clickByNextMonth(count);
        }
        locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));
    }

    public void searchAnyPeriodLocalDate2(String city, String dataFrom, String dataTo) {
        typeCity(city);
        clearPeriod();
        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dataFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dataTo, DateTimeFormatter.ofPattern("M/d/yyyy"));
        click(By.id("dates"));

        int diffMonth = from.getYear() - now.getYear()
                == 0 ? from.getMonthValue() - now.getMonthValue() : 12 - now.getMonthValue() + from.getMonthValue();

        clickByNextMonth(diffMonth);
        String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locator));

        diffMonth = to.getYear() - from.getYear()
                == 0 ? to.getMonthValue() - from.getMonthValue() : 12 - from.getMonthValue() + to.getMonthValue();

        clickByNextMonth(diffMonth);
        locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));
    }


    public void searchAnyPeriodLocalDate(String city, String dataFrom, String dataTo) {
        typeCity(city);
        clearPeriod();
        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dataFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dataTo, DateTimeFormatter.ofPattern("M/d/yyyy"));
        click(By.id("dates"));
        // "8/10/2022", "3/20/2023"
        int diffYear;
        int diffMonth;

        diffYear = from.getYear() - now.getYear();
        if (diffYear == 0) {
            diffMonth = from.getMonthValue() - now.getMonthValue(); /// 8-7 =1
        } else {
            diffMonth = 12 - now.getMonthValue() + from.getMonthValue(); //12-7+3
        }
        clickByNextMonth(diffMonth);
        String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locator));

        ///**************
        diffYear = to.getYear() - from.getYear();

        if (diffYear == 0) {
            diffMonth = to.getMonthValue() - from.getMonthValue();
        } else {
            diffMonth = 12 - from.getMonthValue() + to.getMonthValue();
        }
        clickByNextMonth(diffMonth);
        locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));


    }

    private void clickByNextMonth(int count) {
        for (int i = 0; i < count; i++) {
            click(By.xpath("//button[@aria-label='Next month']"));
        }
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector(".cars-container.ng-star-inserted"));
    }

    public boolean isPageResultAppeared() {
        String currentURL = wd.getCurrentUrl();
        System.out.println(currentURL);
        // https://ilcarro-1578153671498.web.app/search/results?city=Jerusalem&startDate=2022-07-21%2000:00&endDate=2022-07-22%2000:00
        //currentURL.contains("results");
        return currentURL.startsWith("https://ilcarro-1578153671498.web.app/search/results");
    }

    public void searchNextMonth(String city, String dataFrom, String dataTo) {
        typeCity(city);
        clearPeriod();
        click(By.id("dates"));
        click(By.xpath("//button[@aria-label='Next month']"));

        String[] from = dataFrom.split("/");

        String locator = "//div[text()=' " + from[1] + " ']";
        click(By.xpath(locator));

        String[] to = dataTo.split("/");

        String locator2 = String.format("//div[text()=' %s ']", to[1]);

        click(By.xpath(locator2));
    }

    public void selectPeriodPast(String city, String dataFrom, String dataTo) {
        typeCity(city);
        clearPeriod();
        click(By.xpath("//*[@id='dates']"));
        pause(500);
        click(By.xpath("//*[@class='cdk-overlay-backdrop mat-overlay-transparent-backdrop cdk-overlay-backdrop-showing']"));
        type(By.xpath("//*[@id='dates']"), dataFrom + " - " + dataTo);
        click(By.xpath("//*[@class='cdk-overlay-backdrop mat-overlay-transparent-backdrop cdk-overlay-backdrop-showing']"));

    }

    public boolean isMessageErrorPresent() {
        return isElementPresent(By.xpath("//*[@class='ng-star-inserted']"));
    }

    public void clearPeriod() {
        WebElement element = wd.findElement(By.id("dates"));
        String osName = System.getProperty("os.name");
        System.out.println(osName);
        if (osName.startsWith("Mac")) {
            element.sendKeys(Keys.COMMAND, "a");
        } else {
            element.sendKeys(Keys.CONTROL, "a");
        }
        element.sendKeys(Keys.DELETE);
    }
    public boolean isYallaButtonNotActive() {
        boolean disabled = isElementPresent(By.cssSelector("button[disabled]"));
        boolean enabled = wd.findElement(By.cssSelector("[type='submit']")).isEnabled();
        return disabled && !enabled;
    }

    public boolean isPeriodOnPast() {
        return wd.findElement(By.cssSelector("div.error div")).getText().equals("You can't pick date before today");

    }
}
