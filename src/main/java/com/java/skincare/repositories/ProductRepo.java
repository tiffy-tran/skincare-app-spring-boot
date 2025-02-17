package com.java.skincare.repositories;

import org.springframework.data.repository.CrudRepository;

import com.java.skincare.entities.Product;

public interface ProductRepo extends CrudRepository<Product, Integer> {

}
