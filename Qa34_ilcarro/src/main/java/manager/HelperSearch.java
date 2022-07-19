package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperSearch extends HelperBase {
    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    public void searchCurrentMonth(String city, String dataFrom, String dataTo) {
        typeCity(city);
        selectPeriodCurrentMonth(dataFrom, dataTo);
    }

    private void selectPeriodCurrentMonth(String dataFrom, String dataTo) {
        //     "7/25/2022"      "7/30/2022"
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
        click(By.cssSelector(".pac-item"));
        pause(500);
    }

    public void searchCurrentYear(String city, String dataFrom, String dataTo) {
        typeCity(city);
        selectPeriodCurrentYear(dataFrom, dataTo);

    }

    private void selectPeriodCurrentYear(String dataFrom, String dataTo) {
        String now = "7/17/2022";
        String[] dataNow = now.split("/");
        String[] from = dataFrom.split("/");
        String[] to = dataTo.split("/");
        click(By.id("dates"));
        monthСomparison(dataNow[0], from[0]);
        String locator = "//div[text()=' " + from[1] + " ']";
        click(By.xpath(locator));
        monthСomparison(from[0], to[0]);
        String locator2 = String.format("//div[text()=' %s ']", to[1]);
        click(By.xpath(locator2));
    }

    private void monthСomparison(String s, String s1) {
        int one = Integer.parseInt(s);
        int two = Integer.parseInt(s1);

        if (s != s1) {
            for (int i = one; i != two; i++) {
               clickNextMonth();
            }
        }
    }

    private void clickNextMonth() {
        click(By.xpath("//button[@aria-label='Next month']"));
    }
}
