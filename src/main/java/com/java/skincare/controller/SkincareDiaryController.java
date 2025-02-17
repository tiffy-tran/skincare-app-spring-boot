package com.java.skincare.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.skincare.model.ProductInfo;

@Controller
public class SkincareDiaryController {

    // TODO add endpoints for calendar events

    @GetMapping("/products")
    public List<ProductInfo> getProducts(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return List.of();
    }

    @PostMapping("/product")
    public ProductInfo addProduct() {
        return new ProductInfo(null, null, null, null, null, null, null, null, null, null, null);
    }

    @PatchMapping("/product")
    public ProductInfo updateProductInfo() {
        return new ProductInfo(null, null, null, null, null, null, null, null, null, null, null);
    }
}
