package com.java.skincare.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.java.skincare.entities.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.createdAt BETWEEN :startDate AND :endDate")
    public List<Product> findProductsInRange(@Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}
