package com.ecommersbypavan.ecommers.controller;

import com.ecommersbypavan.ecommers.model.Product;
import com.ecommersbypavan.ecommers.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Base64;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    // CREATE with image upload (store image in DB)
    @PostMapping(value = "/products", consumes = {"multipart/form-data"})
    public ResponseEntity<?> createProduct(
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam("image") MultipartFile imageFile
    ) {
        try {
            byte[] imageBytes = imageFile.getBytes();
            Product product = new Product(name, price, imageBytes);
            return ResponseEntity.ok(productRepository.save(product));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }

    // READ all (with Base64 image)
    @GetMapping("/products")
    public List<Map<String, Object>> getProducts() {
        return productRepository.findAll().stream().map(product -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", product.getId());
            map.put("name", product.getName());
            map.put("price", product.getPrice());
            if (product.getImage() != null) {
                map.put("image", Base64.getEncoder().encodeToString(product.getImage()));
            } else {
                map.put("image", null);
            }
            return map;
        }).collect(Collectors.toList());
    }

    // READ one (with Base64 image)
    @GetMapping("/products/{id}")
    public ResponseEntity<Map<String, Object>> getProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", product.getId());
                    map.put("name", product.getName());
                    map.put("price", product.getPrice());
                    if (product.getImage() != null) {
                        map.put("image", Base64.getEncoder().encodeToString(product.getImage()));
                    } else {
                        map.put("image", null);
                    }
                    return ResponseEntity.ok(map);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE with image upload (optional, store in DB)
    @PutMapping(value = "/products/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(name);
                    product.setPrice(price);
                    if (imageFile != null && !imageFile.isEmpty()) {
                        try {
                            product.setImage(imageFile.getBytes());
                        } catch (IOException e) {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
                        }
                    }
                    return ResponseEntity.ok(productRepository.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    // Serve product image as binary
    @GetMapping("/products/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
    return productRepository.findById(id)
        .map(product -> ResponseEntity.ok()
            .header("Content-Type", "image/jpeg")
            .body(product.getImage()))
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
