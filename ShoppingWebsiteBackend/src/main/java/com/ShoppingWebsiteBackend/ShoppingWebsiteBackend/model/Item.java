package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model;


public class Item {
    String itemName;
    int itemCode;
    double price;

    public Item(String itemName, int itemCode, double price) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemCode() {
        return itemCode;
    }

    public double getPrice() {
        return price;
    }
}
