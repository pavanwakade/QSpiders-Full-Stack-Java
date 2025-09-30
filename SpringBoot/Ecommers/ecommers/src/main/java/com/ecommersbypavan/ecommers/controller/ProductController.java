package com.ecommersbypavan.ecommers.controller;

import com.ecommersbypavan.ecommers.model.Product;
import com.ecommersbypavan.ecommers.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    // CREATE
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // READ all
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // READ one
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setPrice(productDetails.getPrice());
                    product.setImageUrl(productDetails.getImageUrl());
                    return ResponseEntity.ok(productRepository.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // CHECKOUT (unchanged, but expects product IDs and quantities)
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody List<OrderItem> items) {
        System.out.println("Received order: " + items);
        return ResponseEntity.ok(Map.of("message", "Order placed successfully!"));
    }

    public static class OrderItem {
        public Long id;
        public int quantity;

        @Override
        public String toString() {
            return "OrderItem{" +
                    "id=" + id +
                    ", quantity=" + quantity +
                    '}';
        }
    }
}
