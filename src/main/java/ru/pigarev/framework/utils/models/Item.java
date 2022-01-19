package ru.pigarev.framework.utils.models;


public class Item extends AbstractProduct {

    public Item(String name) {
        this.name = name;
    }

    public Item(Item item) {
        this.name = item.name;
        this.price = item.price;
        this.waranty = item.waranty;
        this.priceWithWaranty = item.priceWithWaranty;
    }
}
