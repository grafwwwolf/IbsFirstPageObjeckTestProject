package ru.pigarev.framework.utils.models;

public class AbstractProduct {
    protected String name;
    protected int price;
    protected int waranty;
    protected int priceWithWaranty;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getPriceWithWaranty() {
        return priceWithWaranty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPriceWithWaranty(int priceWithWaranty) {
        this.priceWithWaranty = priceWithWaranty;
    }

    public int getTotalPrice() {
        return price + priceWithWaranty;
    }

    public int getWaranty() {
        return waranty;
    }

    public void setWaranty(int waranty) {
        this.waranty = waranty;
    }
}
