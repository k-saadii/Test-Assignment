package org.controller;

import utils.AppProperties;
import utils.UserProperty;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class SeleniumController {
    protected static WebDriver driver;
     AppProperties appProperties = AppProperties.getInstance();

  @BeforeSuite(alwaysRun = true)
    public void setup() {
        if (driver != null) {
            return;
        }

        try {

            String url = appProperties.getProperty(UserProperty.APP_URL_NAME, "http://localhost:3000/");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

            driver.get(url);
            driver.manage().window().maximize();



            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
