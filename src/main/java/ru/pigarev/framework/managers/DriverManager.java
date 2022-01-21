package ru.pigarev.framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.pigarev.framework.utils.PropConst;

public class DriverManager {

    private static DriverManager driverManager = null;
    private TestPropManager propManager = TestPropManager.getInstance();
    private WebDriver driver;

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        if (driverManager == null) {
            driverManager = new DriverManager();
        }
        return driverManager;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private void initDriver() {
        System.setProperty("webdriver.chrome.driver", propManager.getProperty(PropConst.PATH_CHROME_DRIVER_WINDOWS));
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--disable-notifications");
        driver = new ChromeDriver(ops);
    }

    public void quitDriver() {
        driver.manage().deleteAllCookies();
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    }
