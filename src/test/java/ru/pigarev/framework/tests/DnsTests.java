package ru.pigarev.framework.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.pigarev.framework.base_test_class.BaseTest;
import ru.pigarev.framework.pages.*;
import ru.pigarev.framework.utils.models.Item;

import java.math.BigDecimal;

public class DnsTests extends BaseTest {

    Item product = new Item("6.1\" Смартфон Apple iPhone 13 128 ГБ черный");

    private final static CartPage cartPage = CartPage.getInstance();

    HomePage homePage = new HomePage();
    SelectionItemPage selectionItemPage = new SelectionItemPage();
    ItemPage itemPage = new ItemPage();

    @Test
    public void test() {
        homePage.findItemsOnSite("iphone");
        selectionItemPage.selectItem(product.getName());
        product.setWaranty(12);
        product.setPrice(itemPage.getItemPrice());
        System.out.println("Цена первого товара составляет: " + product.getPrice() + " руб.");
        itemPage.chooseService("Гарантия");
        itemPage.chooseWarranty();
        product.setTotalPrice(itemPage.getItemPrice());
        product.setWarantyPrice(product.getTotalPrice() - product.getPrice());
        System.out.println("Цена первого товара составляет: " + product.getTotalPrice() + " руб.");
        itemPage.clickBuyButton();
        cartPage.addProduct(new Item(product));
        product = null;
        itemPage.findItemsOnSite("detroit");
        product = new Item("Игра Detroit: Стать человеком (PS4)");
        selectionItemPage.selectItem(product.getName());
        product.setPrice(itemPage.getItemPrice());
        System.out.println("Цена второго товара составляет: " + product.getPrice() + " руб.");
        product.setTotalPrice(itemPage.getItemPrice());
        System.out.println("Цена второго товара составляет: " + product.getTotalPrice() + " руб.");
        itemPage.clickBuyButton();
        cartPage.addProduct(new Item(product));
        product = null;
//        System.out.println(cartPage.getProdutsInCart());
        System.out.println(itemPage.checkTotalPrice());
        itemPage.goToCart();
        System.out.println(cartPage.checkWarranty(cartPage.getProdutsInCart().get(0).getName()));
        Assertions.assertTrue(cartPage.checkPrices(), "Цены в корзине не совпадают с ценами выбранных товаров");
        cartPage.deleteFromCart(cartPage.getProdutsInCart().get(1).getName());
        System.out.println(cartPage.checkDeleteFromCart(cartPage.getDeletedItem().getName()));
        cartPage.multyplayProductInCart(cartPage.getProdutsInCart().get(0).getName(), 2);
        cartPage.returnLastDeletedProduct();


        BasePage.sleep(5000);

    }

}
