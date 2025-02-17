package com.java.skincare.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class Product {

    @Column(name = "id")
    private Long id;

    @Column(name = "category")
    private String category;

    // TODO this should be the image S3 url
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_alt_str")
    private String imageAltStr;

    @Column(name = "brand")
    private String brand;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private Float price;

    @Column(name = "volume_weight")
    private Float volumeWeight;

    @Column(name = "units")
    private String units;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "purchase_link")
    private String purchaseLink;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
