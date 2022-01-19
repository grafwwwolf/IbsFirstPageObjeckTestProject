package ru.pigarev.framework.base_test_class;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.pigarev.framework.managers.DriverManager;
import ru.pigarev.framework.managers.InitManager;

public class BaseTest {

    private final DriverManager driverManager = DriverManager.getInstance();
//    protected WebDriverWait wait;

    @BeforeAll
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @BeforeEach
    public void beforeEach() {
        String baseUrl = "https://dns-shop.ru";
        driverManager.getDriver().get(baseUrl);
//        wait = new WebDriverWait(driverManager.getDriver(), 17, 1000);
    }

    @AfterEach
    public void afterEach() {
        InitManager.quitFramework();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("@AfterClass -> afterAll()\n");
    }


//    protected WebElement waitUtilElementToBeClickable(WebElement element) {
//        return wait.until(ExpectedConditions.elementToBeClickable(element));
//    }

//    protected void scrollToElementJs(WebElement element) {
//        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driverManager.getDriver();
//        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
//    }
//
//    protected void fillInputField(WebElement element, String value) {
////        scrollToElementJs(element);
//        waitUtilElementToBeClickable(element);
//        element.clear();
//        element.sendKeys(value);
//        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
//        Assertions.assertTrue(checkFlag, "Поле было заполнено некорректно");
//
//    }
//
//    protected void checkCorrect(WebElement element) {
//        WebElement errorInfo = element.findElement(By.xpath("./../../span[@class=\"input__error text--small\"]"));
//        wait.until(ExpectedConditions.textToBePresentInElement(errorInfo, "Введите корректный адрес электронной почты"));
//    }
//
//    public static void sleep(int millis) {
//        try {
//            Thread.sleep(millis);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }


}
