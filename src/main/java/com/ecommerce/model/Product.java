package com.ecommerce.model;

public class Product {
    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;

    public Product() {
    	
    }
    // Constructors, getters và setters
    public Product(int id, String name, String description, String imageUrl, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    // Getters và Setters
    public String getName() {
    	return this.name;
    }
    public String getDescription() {
    	return this.description;
    }
    public String getImageUrl() {
    	return this.imageUrl;
    }
    public double getPrice() {
    	return this.price;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    public void setDescription(String description) {
    	this.description = description;
    }
    public void setImageUrl(String imageUrl) {
    	this.imageUrl = imageUrl;
    }
    public void setPrice(double price) {
    	this.price = price;
    }
    public void setId(int id) {
    	this.id = id;
    }
}
