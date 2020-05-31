package com.kayromino.productservice.service;

import com.kayromino.productservice.entity.Product;
import com.kayromino.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<List<Product>> getAllProducts() {
        List<Product> productList = productRepository.findAll();

        return productList != null ? Optional.of(productList) : Optional.empty();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        product.setDateUpdated(LocalDate.now());
        product = productRepository.save(product);
        return Optional.of(product);
    }

    @Override
    public Optional<Product> addProduct(Product product) {
        product.setDateCreated(LocalDate.now());
        product = productRepository.save(product);
        return Optional.of(product);
    }
}
