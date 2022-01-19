package ru.pigarev.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.pigarev.framework.managers.DriverManager;

public class BasePage {

    protected final DriverManager driverManager = DriverManager.getInstance();

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();

    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);



    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void fillInputField(WebElement element, String value) {
        scrollToElementJs(element);
        waitUtilElementToBeClickable(element);
        element.clear();
        element.sendKeys(value);
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        Assertions.assertTrue(checkFlag, "Поле было заполнено некорректно");

    }

    protected void checkCorrect(WebElement element) {
        WebElement errorInfo = element.findElement(By.xpath("./../../span[@class=\"input__error text--small\"]"));
        wait.until(ExpectedConditions.textToBePresentInElement(errorInfo, "Введите корректный адрес электронной почты"));
    }

    protected int getCount(WebElement element) {
        if (element.getText() == null) {
            return 0;
        } else return Integer.parseInt(element.getText());
    }

    public int textAsInt(WebElement element) {
        return Integer.parseInt(element.getText().replaceAll("\\D+", ""));
    }

    public int getItemPrice(String priceStr) {
        String strPrice = priceStr.split("₽")[0];
        System.out.println(strPrice);
//        float price = Float.parseFloat(strPrice);
//        double value = Double.parseDouble(strPrice);
//        BigDecimal price = new BigDecimal(value).setScale(2);
        return Integer.parseInt(strPrice.replaceAll("\\D+", ""));
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
