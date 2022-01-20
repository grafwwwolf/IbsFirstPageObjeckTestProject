package ru.pigarev.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.pigarev.framework.utils.models.Item;

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

    public ItemPage getItemPrice() {
        String strPrice = itemPrice.getText().split("₽")[0];
//        float price = Float.parseFloat(strPrice);
//        double value = Double.parseDouble(strPrice);
//        BigDecimal price = new BigDecimal(value).setScale(2);
        int price = Integer.parseInt(strPrice.replaceAll("\\D+", ""));
        itemManager.getProduct().setPrice(price);
        itemManager.getProduct().setTotalPrice(price);
        return this;
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

//    public ItemPage chooseWarranty() {
//        Item product = itemManager.getProduct();
//        waitUtilElementToBeClickable(listWarranty.get(1)).click();
//        boolean boolSel = wait.until(ExpectedConditions.elementToBeSelected(listWarranty.get(1).findElement(By.xpath("./../input"))));
//        Assertions.assertTrue(boolSel, "Гарантия не кликнулась");
//        WebElement changeWarantyPrice = itemPrice.findElement(By.xpath("./..//div[@class=\"product-buy__sub\"]"));
//        wait.until(ExpectedConditions.textToBePresentInElement(changeWarantyPrice, "цена изменена"));
//        product.setTotalPrice(Integer.parseInt(itemPrice.getText().split("₽")[0].replaceAll("\\D+", "")));
//        product.setWarantyPrice(product.getTotalPrice() - product.getPrice());
//        System.out.println(itemManager.getProduct().getPrice());
//        System.out.println(itemManager.getProduct().getwarantyPrice());
//        System.out.println(itemManager.getProduct().getTotalPrice());
//        System.out.println("сверху цена, цена гарантии и итоговая стоимость");
//        System.out.println("-----------------");
//        return this;
//    }

    public ItemPage chooseWarrantyImproove() {
        boolean findWanaty = false;
        Item product = itemManager.getProduct();
        int warantyOnSite = 0;
        int count = 0;
        for (WebElement element : listWarranty) {
            if (count == 0) {
                count++;
                continue;
            }
            warantyOnSite = Integer.parseInt(element.getText().replaceAll("\\D+", ""));
            if (warantyOnSite == product.getWaranty()) {
                waitUtilElementToBeClickable(element).click();
                boolean boolSel = wait.until(ExpectedConditions.elementToBeSelected(element.findElement(By.xpath("./../input"))));
                Assertions.assertTrue(boolSel, "Гарантия не кликнулась");
                WebElement changeWarantyPrice = itemPrice.findElement(By.xpath("./..//div[@class=\"product-buy__sub\"]"));
                wait.until(ExpectedConditions.textToBePresentInElement(changeWarantyPrice, "цена изменена"));
                product.setTotalPrice(Integer.parseInt(itemPrice.getText().split("₽")[0].replaceAll("\\D+", "")));
                product.setWarantyPrice(product.getTotalPrice() - product.getPrice());
                findWanaty = true;
                break;
            }
        }
        Assertions.assertTrue(findWanaty, "Необходимая гарантия для данного продукта отсутствует");

        return this;
    }


    public ItemPage clickBuyButton() {
        Item product = itemManager.getProduct();
        if (cartCount.getAttribute("class").contains("empty")) {
            js.executeScript("arguments[0].click();", buyButton);
            wait.until(ExpectedConditions.attributeToBe(cartCount, "class", "cart-link__badge"));
        } else {
            js.executeScript("arguments[0].click();", buyButton);
            wait.until(ExpectedConditions.attributeToBe(cartCount, "textContent", String.valueOf(count + 1)));
        }
        Assertions.assertTrue(getCount(cartCount) > count, "Кнопка Купить не нажалась");
        count = getCount(cartCount);
        pageManager.getCartPage().addProduct(new Item(product));
        itemManager.closeProduct();
        return this;
    }

    public SelectionItemPage findItemsOnSite(String item) {
        fillInputField(searchLine, item);
        searchLine.submit();
        Assertions.assertTrue(driverManager.getDriver().getTitle().contains(item), "Страница не загрузилась");
        return pageManager.getSelectionItemPage();
    }

    public ItemPage checkTotalPrice() {
        Assertions.assertTrue(checkTotal(), "Цена корзины не совпадает со стоимостью покупок.");
        return this;
    }

    private boolean checkTotal() {
        int totalPrice = 0;
        for (Item item : pageManager.getCartPage().getProdutsInCart()) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice == Integer.parseInt(cartPrice.getText().replaceAll("\\D+", ""));

    }

    public CartPage goToCart() {
        waitUtilElementToBeClickable(cart).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class=\"cart-title\"]")));
        Assertions.assertEquals("Корзина", cartPage.getH1TitleFromCartPage(), "Страница корзины не загрузилась.");
        return pageManager.getCartPage();
    }

}
