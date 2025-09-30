package com.ecommersbypavan.ecommers.repository;

import com.ecommersbypavan.ecommers.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}