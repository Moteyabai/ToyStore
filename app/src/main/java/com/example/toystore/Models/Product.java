package com.example.toystore.Models;

public class Product {

    private int productID;
    private String productName;
    private double price;
    private String description;

    private String image;

    public Product(int productID, String productName, double price, String description, String image) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
