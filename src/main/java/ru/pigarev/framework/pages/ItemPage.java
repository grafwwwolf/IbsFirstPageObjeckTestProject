package ru.pigarev.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.pigarev.framework.utils.models.Item;

import java.math.BigDecimal;
import java.util.List;

public class ItemPage extends BasePage {

    private static int count;
    private final static CartPage cartPage = CartPage.getInstance();

    @FindBy(xpath = "//div[@class=\"product-buy product-buy_one-line\"]/div//div[contains(@class, \"product-buy__price\")]")
    private WebElement itemPrice;

    @FindBy(xpath = "//div[@class=\"additional-sales-tabs__titles-wrap\"]/div[contains(text(), additional-sales-tabs__title)]")
    private List<WebElement> serviceMenu;

    @FindBy(xpath = "//span[@class=\"ui-radio__content\"]")
    private List<WebElement> listWarranty;

    @FindBy(xpath = "//div[@class=\"product-buy product-buy_one-line\"]//button[contains(@class, \"buy-btn\")]")
    private WebElement buyButton;

    @FindBy(xpath = "//a[@data-commerce-target=\"CART\"]/span[contains(@class, \"cart-link__badge\")]")
    private WebElement cartCount;

    @FindBy(xpath = "//div[@class=\"presearch__suggests presearch__suggests_hidden\"]/../ input[@class=\"ui-input-search__input ui-input-search__input_presearch\"]")
    private WebElement searchLine;

    @FindBy(xpath = "//span[@class=\"cart-link__price\"]")
    private WebElement cartPrice;

    @FindBy(xpath = "//a[@class=\"ui-link cart-link\"]")
    private WebElement cart;

    public int getItemPrice() {
        String strPrice = itemPrice.getText().split("₽")[0];
        System.out.println(strPrice);
//        float price = Float.parseFloat(strPrice);
//        double value = Double.parseDouble(strPrice);
//        BigDecimal price = new BigDecimal(value).setScale(2);
        return Integer.parseInt(strPrice.replaceAll("\\D+", ""));
    }

    public ItemPage chooseService(String serviceName) {
        for (WebElement service : serviceMenu) {
            if (service.getText().contains(serviceName)) {
                waitUtilElementToBeClickable(service).click();
                Assertions.assertTrue(service.getAttribute("class").contains("additional-sales-tabs__title_active"), "Страница не загрузилась");
                return this;
            }
        }
        Assertions.fail("Вкладка '" + serviceName + "' не была найдена в меню!");
        return this;
    }

    public ItemPage chooseWarranty() {
        waitUtilElementToBeClickable(listWarranty.get(1)).click();
        boolean boolSel = wait.until(ExpectedConditions.elementToBeSelected(listWarranty.get(1).findElement(By.xpath("./../input"))));
        Assertions.assertTrue(boolSel, "Гарантия не кликнулась");  //почему-то false


//        if (listWarranty.size() == 1) {
//            return this;
//        }
//
//        for (WebElement warranty : listWarranty) {
//            String warrantyStr = warranty.findElement(By.xpath("./span")).getText();
//            int warrantyInMounth = Integer.parseInt(warrantyStr.split(" ")[0]);
//        }
//        Assertions.fail("Вкладка '" +  + "' не была найдена в меню!");
        return this;
    }


    public void clickBuyButton() {
        if (cartCount.getAttribute("class").contains("empty")) {
            System.out.println("корзина пуста");
            js.executeScript("arguments[0].click();", buyButton);
            wait.until(ExpectedConditions.attributeToBe(cartCount, "class", "cart-link__badge"));
        } else {
            System.out.println(cartCount.getText());
            js.executeScript("arguments[0].click();", buyButton);
            wait.until(ExpectedConditions.attributeToBe(cartCount, "textContent", String.valueOf(count + 1)));
        }
        System.out.println(getCount(cartCount));
        Assertions.assertTrue(getCount(cartCount) > count, "Кнопка Купить не нажалась");
        count = getCount(cartCount);
    }

    public void findItemsOnSite(String item) {
        fillInputField(searchLine, item);
        searchLine.submit();
        Assertions.assertTrue(driverManager.getDriver().getTitle().contains(item), "Страница не загрузилась");
    }

    public boolean checkTotalPrice() {
        int totalPrice = 0;
        for (Item item : cartPage.getProdutsInCart()) {
            totalPrice += item.getTotalPrice();
        }
        System.out.println(totalPrice);
        System.out.println(Integer.parseInt(cartPrice.getText().replaceAll("\\D+", "")));
        return totalPrice == Integer.parseInt(cartPrice.getText().replaceAll("\\D+", ""));
    }

    public CartPage goToCart() {
        waitUtilElementToBeClickable(cart).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class=\"cart-title\"]")));
        Assertions.assertEquals( "Корзина", cartPage.getH1TitleFromCartPage(), "Страница корзины не загрузилась.");
        return cartPage;
    }

}
