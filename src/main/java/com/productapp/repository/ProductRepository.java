package com.productapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productapp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
