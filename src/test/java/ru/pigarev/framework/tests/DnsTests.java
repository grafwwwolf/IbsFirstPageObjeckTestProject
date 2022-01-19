package ru.pigarev.framework.tests;

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
        product.setPriceWithWaranty(itemPage.getItemPrice());
        System.out.println("Цена первого товара составляет: " + product.getPriceWithWaranty() + " руб.");
        itemPage.clickBuyButton();
        cartPage.addProduct(new Item(product));
        product = null;
        itemPage.findItemsOnSite("detroit");
        product = new Item("Игра Detroit: Стать человеком (PS4)");
//        product.setName("Игра Detroit: Стать человеком (PS4)");
        selectionItemPage.selectItem(product.getName());
        product.setPrice(itemPage.getItemPrice());
        System.out.println("Цена второго товара составляет: " + product.getPrice() + " руб.");
        product.setPriceWithWaranty(itemPage.getItemPrice());
        System.out.println("Цена второго товара составляет: " + product.getPriceWithWaranty() + " руб.");
        itemPage.clickBuyButton();
        cartPage.addProduct(new Item(product));
        product = null;
        System.out.println(itemPage.checkTotalPrice());
        itemPage.goToCart();
        System.out.println(cartPage.checkWarranty(cartPage.getProdutsInCart().get(0).getName()));


        BasePage.sleep(5000);

    }

//    @Test
//    public void test() {
//        homePage.findItemsOnSite("iphone");
////        selectionItemPage.selectItem("6.1\" Смартфон Apple iPhone 13 128 ГБ черный");
//        selectionItemPage.selectItem(product.getName());
////        int firstItemPrice = itemPage.getItemPrice();
//        product.setPrice(itemPage.getItemPrice());
////        System.out.println("Цена первого товара составляет: " + firstItemPrice + ".");
//        System.out.println("Цена первого товара составляет: " + product.getPrice() + ".");
//        itemPage.chooseService("Гарантия");
//        itemPage.chooseWarranty();
////        int firstItemPriceWithWarranty = itemPage.getItemPrice();
//        product.setPriceWithWaranty(itemPage.getItemPrice());
////        System.out.println("Цена первого товара составляет: " + firstItemPriceWithWarranty + ".");
//        System.out.println("Цена первого товара составляет: " + product.getPriceWithWaranty() + ".");
//        itemPage.clickBuyButton();
//        itemPage.findItemsOnSite("detroit");
//        product.setName("Игра Detroit: Стать человеком (PS4)");
////        selectionItemPage.selectItem("Игра Detroit: Стать человеком (PS4)");
//        selectionItemPage.selectItem(product.getName());
//        product.setPrice(itemPage.getItemPrice());
////        firstItemPrice = itemPage.getItemPrice();
////        System.out.println("Цена второго товара составляет: " + firstItemPrice + ".");
//        System.out.println("Цена второго товара составляет: " + product.getPrice() + ".");
//        itemPage.clickBuyButton();
//
//
//        BasePage.sleep(5000);
//
//    }
}
