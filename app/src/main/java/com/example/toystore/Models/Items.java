package com.example.toystore.Models;

public class Items {
    private int id;
    private String itemName;
    private String itemImage;
    private int quantity;
    private double price;


    public Items(int id, String itemName, String itemImage, int quantity, double price) {
        this.id = id;
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
