package com.kayromino.productservice.service;

import com.kayromino.productservice.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<List<Product>> getAllProducts();
    Optional<Product> getProductById(Long id);
    Optional<Product> updateProduct(Product product);
    Optional<Product> addProduct(Product product);
}
