package ru.pigarev.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SelectionItemPage extends BasePage {

    @FindBy(xpath = "//a[@class=\"catalog-product__name ui-link ui-link_black\"]/span")
    private List<WebElement> listCatalog;

    public SelectionItemPage selectItem(String itemName) {
        for (WebElement item : listCatalog) {
            if (item.getText().contains(itemName)) {
                waitUtilElementToBeClickable(item).click();
                Assertions.assertTrue(driverManager.getDriver().getTitle().contains("Купить " + itemName), "Страница не загрузилась");
                return this;
            }
        }
        Assertions.fail("Товар '" + itemName + "' не был найден в каталоге!");
        return this;
    }
}
