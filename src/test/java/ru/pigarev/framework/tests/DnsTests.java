package ru.pigarev.framework.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.pigarev.framework.base_test_class.BaseTests;

@Tag("All")
@DisplayName("Набор кейсов сайта ДНС")
public class DnsTests extends BaseTests {


    @Test
    @Tag("firstTest")
    @DisplayName("Первый кейс")
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
