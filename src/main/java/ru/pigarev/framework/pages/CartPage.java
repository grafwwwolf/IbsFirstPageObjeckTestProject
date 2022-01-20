package ru.pigarev.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.pigarev.framework.utils.models.Item;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private static CartPage cartPage = null;
    private List<Item> produtsInCart;
    private Item deletedItem;
    private int totalPriceDeforeDeleting;

    @FindBy(xpath = "//h1[@class=\"cart-title\"]")
    private WebElement h1Title;

    @FindBy(xpath = "//a[@class=\"cart-items__product-name-link\"]")
    private List<WebElement> listProduct;

    @FindBy(xpath = "//a[@data-commerce-target=\"CART\"]/span[contains(@class, \"cart-link__badge\")]")
    private WebElement cartCount;

    @FindBy(xpath = "//span[@class=\"cart-link__price\"]")
    private WebElement cartPrice;

    @FindBy(xpath = "//span[@class=\"cart-tab-menu__item cart-tab-menu__item_print-cart\"]/span")
    private WebElement returnDeletedProduct;

    public Item getDeletedItem() {
        return deletedItem;
    }

    public CartPage() {
        produtsInCart = new ArrayList<>();
    }

    public static CartPage getInstance() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }


    public void addProduct(Item product) {
        produtsInCart.add(product);
    }

    public List<Item> getProdutsInCart() {
        return produtsInCart;
    }

    public String getH1TitleFromCartPage() {
        return h1Title.getText();
    }

    public CartPage checkWarranty(Item product) {
        String productName = product.getName();
        Assertions.assertTrue(
         searchItemInCartListProduct(productName).getWaranty() == checCurrentkWarranty(searchProduct(productName)),
                "Гарантия не совпадает."
        );
        return this;
    }

    public WebElement searchProduct(String productName) {
        for (WebElement element : listProduct) {
            if (productName.equalsIgnoreCase(element.getText())) {
                return element;
            }
        }
        return null;
    }

    public int checCurrentkWarranty(WebElement element) {
        String warranty = element.findElement(By.xpath("./../../../../../..//span[contains(@class, 'base-ui-radio-button__icon_checked')]")).getText();
        int warantyInt = Integer.parseInt(warranty.replaceAll("\\D+", ""));
        return warantyInt;
    }

    public Item searchItemInCartListProduct(String productName) {
        for (Item item : produtsInCart) {
            if (item.getName().equalsIgnoreCase(productName)) {
                return item;
            }
        }
        return null;
    }

    public int getCurrentPrice(WebElement element) {
        String priceStr = element.findElement(By.xpath("./../../../../..//span[@class=\"price__current\"]")).getText();
        return getItemPrice(priceStr);
    }

    public CartPage checkPricesForAllItems() {
        Assertions.assertTrue(checkPrices(), "Цены в корзине не совпадают с ценами выбранных товаров");
        return this;
    }

    public boolean checkPrices() {
        for (Item item : produtsInCart) {
            if (item.getPrice() == getCurrentPrice(searchProduct(item.getName()))) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public CartPage deleteFromCart(Item product) {
        String productName = product.getName();
        int productCount = Integer.parseInt(cartCount.getText());
        totalPriceDeforeDeleting = Integer.parseInt(cartPrice.getText().replaceAll("\\D+", ""));
        WebElement deleteProduct = searchProduct(productName).findElement(By.xpath("./../..//button[contains(@class, \"remove-button\")]"));
        waitUtilElementToBeClickable(deleteProduct).click();
        removeFromProdutsInCart(productName);

        if (productCount > 1) {
            wait.until(ExpectedConditions.attributeToBe(cartCount, "textContent", String.valueOf(productCount - 1)));
            int newProductCount = Integer.parseInt(cartCount.getText());
            Assertions.assertTrue((newProductCount == productCount - 1) && (newProductCount == produtsInCart.size()), "Удаление товара из корзины не произошло.");
        } else {
            wait.until(ExpectedConditions.attributeContains(cartCount, "class", "empty"));
            Assertions.assertTrue(cartCount.getAttribute("class").contains("empty") && produtsInCart.size() == 0, "Удаление товара из корзины не произошло.");
        }
        return this;
    }

    public void removeFromProdutsInCart(String productName) {
        for (Item items : produtsInCart) {
            if (items.getName().equalsIgnoreCase(productName)) {
                deletedItem = items;
                produtsInCart.remove(items);
                return;
            }
        }
    }

    public CartPage assertDeleteFromCart() {
        Assertions.assertTrue(checkDeleteFromCart(),
                " Удаление Продукта " + deletedItem.getName() + " произошло с ошибкой. Или не поменялась цена, или продукт остался виден в корзине");
        return this;
    }

    public boolean checkDeleteFromCart() {
        String productName = deletedItem.getName();
        int currentCartPrice = Integer.parseInt(cartPrice.getText().replaceAll("\\D+", ""));
        if ((searchProduct(productName) == null) && (currentCartPrice == totalPriceDeforeDeleting - deletedItem.getTotalPrice())) {
            return true;
        } else {
            return false;
        }
    }

    public CartPage multyplayProductInCart(Item product, int count) {
        String productName = product.getName();
        Item item = searchItemInCartListProduct(productName);
        int checkSum = (item.getTotalPrice()) * (count + 1);
        WebElement multyplyButton = searchProduct(productName).findElement(By.xpath("./../../../../..//i[contains(@class, \"count-buttons__icon-plus\")]"));
        for (int i = 0; i < count; i++) {
            waitUtilElementToBeClickable(multyplyButton).click();
            wait.until(ExpectedConditions.attributeToBe(cartCount, "textContent", String.valueOf(produtsInCart.size() + 1)));
            produtsInCart.add(new Item(item));
        }
        Assertions.assertTrue(getCartPrice() == checkSum, "Добавление кнопкой не произошло");
        return this;
    }

    public int getCartPrice() {
        return Integer.parseInt(cartPrice.getText().replaceAll("\\D+", ""));
    }

    public void returnLastDeletedProduct() {
        int currentCartPrice = getCartPrice();
        waitUtilElementToBeClickable(returnDeletedProduct).click();
        wait.until(ExpectedConditions.attributeToBe(cartCount, "textContent", String.valueOf(produtsInCart.size() + 1)));
        produtsInCart.add(new Item(deletedItem));
        Assertions.assertTrue(getCartPrice() == (currentCartPrice + deletedItem.getTotalPrice()), "Возврат элемента не произошел");
    }
}
