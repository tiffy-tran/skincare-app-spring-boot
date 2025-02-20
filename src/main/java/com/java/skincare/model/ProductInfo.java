package com.java.skincare.model;

public record ProductInfo(
        Long id,
        ProductCategory parentCategory,
        ProductCategory subCategory,
        String imageUrl,
        String imageAltStr,
        String brand,
        String productName,
        Float price,
        Float volumeWeight,
        String units,
        String countryOfOrigin,
        String purchaseLink,
        String description) {
}
