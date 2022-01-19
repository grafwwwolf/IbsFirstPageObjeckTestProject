package ru.pigarev.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.pigarev.framework.managers.DriverManager;
import ru.pigarev.framework.utils.models.Item;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private static CartPage cartPage = null;
    private List<Item> produtsInCart;

    @FindBy(xpath = "//h1[@class=\"cart-title\"]")
    private WebElement h1Title;

    @FindBy(xpath = "//a[@class=\"cart-items__product-name-link\"]")
    private List<WebElement> listProduct;

    public CartPage() {
        produtsInCart = new ArrayList<>();
    }

    public static CartPage getInstance() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }

//    public void setProdutsInCart(List<Item> produtsInCart) {
//        this.produtsInCart = produtsInCart;
//    }

    public void addProduct(Item product) {
        produtsInCart.add(product);
    }

    public List<Item> getProdutsInCart() {
        return produtsInCart;
    }

    public String getH1TitleFromCartPage() {
        return h1Title.getText();
    }

    public boolean checkWarranty(String productName) {
        return searchItemInCartListProduct(productName).getWaranty() == checCurrentkWarranty(searchProduct(productName));
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
        System.out.println(warranty);
        int warantyInt = Integer.parseInt(warranty.replaceAll("\\D+", ""));
        System.out.println(warantyInt);
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
        String priceStr = element.findElement(By.xpath("//a[@class=\"cart-items__product-name-link\"]/../../../../..//span[@class=\"price__current\"]")).getText();
        return getItemPrice(priceStr);
    }

    public boolean checkPrices() {
        for (Item item : produtsInCart) {
            if (item.getTotalPrice() == getCurrentPrice(searchProduct(item.getName()))) {
                continue;
            } else
                Assertions.fail("Цены в корзине не совпадают с ценами выбранных товаров");
        }
        return true;
    }

}
