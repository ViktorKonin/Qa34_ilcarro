package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ApplicationManager {

    WebDriver wd;
    HelperUser helperUser;
    HelperCar car;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    HelperSearch search;
    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(Browser.CHROME.browserName())) {
            wd = new ChromeDriver();
        } else if (browser.equals(Browser.EDGE.browserName())) {
            wd = new EdgeDriver();
        }

        WebDriverListener listener = new MyListener();
        wd = new EventFiringDecorator(listener).decorate(wd);
        logger.info("All tests run in Chrome browser.");
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wd.navigate().to("https://ilcarro-1578153671498.web.app/search");
        logger.info("Current Url-->>>" + wd.getCurrentUrl());
        helperUser = new HelperUser(wd);
        car = new HelperCar(wd);
        search = new HelperSearch(wd);
    }

    public void stop() {
        // wd.quit();
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperCar car() {
        return car;
    }

    public HelperSearch search() {
        return search;
    }

    public void submitWitoutWait() {
    }


}
