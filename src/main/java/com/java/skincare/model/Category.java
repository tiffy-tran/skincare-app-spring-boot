package com.java.skincare.model;

public enum Category {

    SUNSCREEN("Sunscreen"),
    SERUM("Serum"),
    MOISTURIZER("Moisturizer"),
    CLEANSER("Cleanser"),
    TONER("Toner");

    private final String category;

    private Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

}
