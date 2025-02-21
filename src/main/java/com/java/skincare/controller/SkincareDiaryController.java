package com.java.skincare.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.skincare.model.ProductInfo;
import com.java.skincare.service.SkincareDiaryService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/skincare-diary")
@Tag(name = "Products", description = "Product management APIs")
public class SkincareDiaryController {

    // TODO add endpoints for calendar events
    // TODO add endpoint to delete product? soft delete?

    @Autowired
    private SkincareDiaryService skincareDiaryService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductInfo>> getProducts(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            // TODO since either date can be null, figure out how to handle that case
            List<ProductInfo> pis;
            if (startDate == null && endDate == null) {
                pis = skincareDiaryService.getAllProducts();
            } else {
                pis = skincareDiaryService.getProductsInRange(startDate, endDate);
            }
            return ResponseEntity.ok(pis);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ProductInfo> addProduct(@RequestBody ProductInfo productInfo) {
        try {
            ProductInfo pi = skincareDiaryService.addProduct(productInfo);
            return ResponseEntity.ok(pi);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PatchMapping("/updateProduct")
    public ResponseEntity<ProductInfo> updateProductInfo(@RequestBody ProductInfo updatedProductInfo) {
        try {
            ProductInfo pi = skincareDiaryService.updateProduct(updatedProductInfo);
            return ResponseEntity.ok(pi);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
