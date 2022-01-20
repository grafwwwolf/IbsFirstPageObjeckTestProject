package ru.pigarev.framework.tests;

import org.junit.jupiter.api.Test;
import ru.pigarev.framework.base_test_class.BaseTest;


public class DnsTests extends BaseTest {


    @Test
    public void test() {

        pageManager.getHomePage().findItemsOnSite("iphone")
        .selectItem(product)
        .getItemPrice()
        .chooseService("Гарантия")
        .chooseWarrantyImproove()
        .clickBuyButton()
        .findItemsOnSite("detroit")
        .selectItem(detroit)
        .getItemPrice()
        .clickBuyButton()
        .checkTotalPrice()
        .goToCart()
        .checkWarranty(product)
        .checkPricesForAllItems()
        .deleteFromCart(detroit)
        .assertDeleteFromCart()
        .multyplayProductInCart(product, 2)
        .returnLastDeletedProduct();

    }

}
