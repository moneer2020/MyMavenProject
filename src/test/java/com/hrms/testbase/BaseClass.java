package com.hrms.testbase;

import com.hrms.utils.ConfigurationReader;
import com.hrms.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    public static WebDriver driver;

    /**
     * This method will open the specified browser and navigate to the specified URL
     *
     * @return WebDriver
     */
    public static WebDriver setUp() {
        ConfigurationReader.readConfiguration(Constants.CONFIGURATION_FILEPATH);

        switch (ConfigurationReader.getPropertyValue("browser").toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions=new ChromeOptions();
                chromeOptions.setHeadless(true);
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "internet":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            default:
                throw new RuntimeException("Choose from Internet Explorer, Firefox or Chrome");
        }

        driver.navigate().to(ConfigurationReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Constants.WAIT, TimeUnit.SECONDS);
        PageInitializer.initializePageObjects();
        return driver;
    }

    /**
     * This method will close any open browser
     */
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
