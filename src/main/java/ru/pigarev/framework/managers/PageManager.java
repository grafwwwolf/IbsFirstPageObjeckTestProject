package ru.pigarev.framework.managers;

import ru.pigarev.framework.pages.CartPage;
import ru.pigarev.framework.pages.HomePage;
import ru.pigarev.framework.pages.ItemPage;
import ru.pigarev.framework.pages.SelectionItemPage;

public class PageManager {

    private static PageManager pageManager = null;
    private HomePage homePage;
    private SelectionItemPage selectionItemPage;
    private ItemPage itemPage;
    private CartPage cartPage;

    private PageManager() {
    }

    public static PageManager getInstance() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public SelectionItemPage getSelectionItemPage() {
        if (selectionItemPage == null) {
            selectionItemPage = new SelectionItemPage();
        }
        return selectionItemPage;
    }

    public ItemPage getItemPage() {
        if (itemPage == null) {
            itemPage = new ItemPage();
        }
        return itemPage;
    }

    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }
}
