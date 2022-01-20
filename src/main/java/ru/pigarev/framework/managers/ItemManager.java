package ru.pigarev.framework.managers;

import ru.pigarev.framework.utils.models.Item;

public class ItemManager {

    private static ItemManager itemManager = null;
    private Item product = null;

    private ItemManager() {

    }

    public static ItemManager getInstance() {
        if (itemManager == null) {
            itemManager = new ItemManager();
        }
        return itemManager;
    }

    public Item getProduct(Item item) {
        if (product == null) {
            product = new Item(item);
        }
        return product;
    }

    public Item getProduct() {
        return product;
    }

    public void closeProduct() {
        product = null;
    }


}
