package ru.pigarev.framework.utils.models;

public class AbstractProduct {
    protected String name;
    protected int price;
    protected int waranty;
    protected int warantyPrice;
    protected int totalPrice;


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public AbstractProduct() {
    }

    public AbstractProduct(String name, int price, int waranty, int warantyPrice, int totalPrice) {
        this.name = name;
        this.price = price;
        this.waranty = waranty;
        this.warantyPrice = warantyPrice;
        this.totalPrice = totalPrice;
    }

    public int getwarantyPrice() {
        return warantyPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setwarantyPrice(int warantyPrice) {
        this.warantyPrice = warantyPrice;
    }

    public int getTotalPrice() {
        return this.totalPrice;
    }

    public void setWarantyPrice(int warantyPrice) {
        this.warantyPrice = warantyPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getWaranty() {
        return waranty;
    }

    public void setWaranty(int waranty) {
        this.waranty = waranty;
    }
}
