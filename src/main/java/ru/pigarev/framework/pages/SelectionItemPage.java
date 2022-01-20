package ru.pigarev.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.pigarev.framework.utils.models.Item;

import java.util.List;

public class SelectionItemPage extends BasePage {

    @FindBy(xpath = "//a[@class=\"catalog-product__name ui-link ui-link_black\"]/span")
    private List<WebElement> listCatalog;

    public ItemPage selectItem(Item product) {
        String itemName = itemManager.getProduct(product).getName();
        for (WebElement item : listCatalog) {
            if (item.getText().contains(itemName)) {
                waitUtilElementToBeClickable(item).click();
                Assertions.assertTrue(driverManager.getDriver().getTitle().contains("Купить " + itemName), "Страница не загрузилась");
                return pageManager.getItemPage();
            }
        }
        Assertions.fail("Товар '" + itemName + "' не был найден в каталоге!");
        return null;
    }
}
