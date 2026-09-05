package com.ecommerce.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getters and Setters (Encapsulation)
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-15s | Price: ₹%-7.2f | Stock: %d", id, name, price, stock);
    }
}