package com.java.skincare.service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.java.skincare.entities.Product;
import com.java.skincare.model.ProductCategory;
import com.java.skincare.model.ProductInfo;
import com.java.skincare.repositories.ProductRepo;

//TODO add logging
@Service
public class SkincareDiaryService {

    @Autowired
    private ProductRepo productRepo;

    public ProductInfo addProduct(ProductInfo productInfo) {
        try {
            Product product = toProduct(productInfo);
            productRepo.save(product);
            return toProductInfo(product);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Database integrity violation: " + e.getMessage());
        }
    }

    public List<ProductInfo> getProductsInRange(LocalDate startDate, LocalDate endDate) {
        try {
            return productRepo.findProductsInRange(startDate, endDate).stream()
                    .map(this::toProductInfo).toList();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error retrieving products within date range (startDate: " + startDate
                    + "endDate:" + endDate + "):" + e.getMessage());
        }
    }

    public List<ProductInfo> getAllProducts() {
        try {
            return StreamSupport.stream(productRepo.findAll().spliterator(), false)
                    .map(this::toProductInfo).toList();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error retrieving all products: " + e.getMessage());
        }
    }

    // TODO should we allow nulls in DB? this assumes that if any field is null, we
    // don't update that field.
    public ProductInfo updateProduct(ProductInfo updatedProductDetails) {
        Optional<Product> existingProductOptional = productRepo.findById(updatedProductDetails.id());

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();

            // TODO figure out a way to update only update certain fields.
            // Loop through record fields and check for null values
            for (Field field : ProductInfo.class.getDeclaredFields()) {
                try {
                    field.setAccessible(true); // TODO need? allows access to private fields
                    Object value = field.get(updatedProductDetails);
                    if (value != null) {
                        Field entityField = Product.class.getDeclaredField(field.getName());
                        entityField.setAccessible(true); // Allow access to private fields
                        entityField.set(existingProduct, value);
                    }
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    // TODO add a logger here
                    e.printStackTrace();
                }
            }
            Product updatedProduct = productRepo.save(existingProduct);
            return toProductInfo(updatedProduct);
        } else {
            throw new RuntimeException("Product not found with id: " + updatedProductDetails.id());
        }
    }

    private ProductInfo toProductInfo(Product product) {
        return new ProductInfo(
                product.getId(),
                ProductCategory.valueOf(product.getParentCategory()),
                ProductCategory.valueOf(product.getSubCategory()),
                product.getImageUrl(),
                product.getImageAltStr(),
                product.getBrand(),
                product.getProductName(),
                product.getPrice(),
                product.getVolumeWeight(),
                product.getUnits(),
                product.getCountryOfOrigin(),
                product.getPurchaseLink(),
                product.getDescription() == null ? "" : product.getDescription());
    }

    private Product toProduct(ProductInfo productInfo) {
        Product product = new Product();
        product.setParentCategory(productInfo.parentCategory().name());
        product.setSubCategory(productInfo.subCategory().name());
        product.setImageUrl(productInfo.imageUrl());
        product.setImageAltStr(productInfo.imageAltStr());
        product.setBrand(productInfo.brand());
        product.setProductName(productInfo.productName());
        product.setPrice(productInfo.price());
        product.setVolumeWeight(productInfo.volumeWeight());
        product.setUnits(productInfo.units());
        product.setCountryOfOrigin(productInfo.countryOfOrigin());
        product.setPurchaseLink(productInfo.purchaseLink());
        product.setDescription(productInfo.description());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }

}
