package com.safari.destinow.login;

// Item.java
public class Item {
    private String name;
    private String imageUrl;

    public Item() {
        // Default constructor required for Firebase
    }

    public Item(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

