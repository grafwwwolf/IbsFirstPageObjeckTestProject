package ru.pigarev.framework.utils.models;


public class Item extends AbstractProduct {

    public Item(String name) {
        this.name = name;
    }



    public Item(Item item) {
        this.name = item.name;
        this.price = item.price;
        this.waranty = item.waranty;
        this.warantyPrice = item.warantyPrice;
        this.totalPrice = item.totalPrice;
    }

    public Item(String name, int price, int waranty, int warantyPrice, int totalPrice) {
        super(name, price, waranty, warantyPrice, totalPrice);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", waranty=" + waranty +
                ", warantyPrice=" + warantyPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
