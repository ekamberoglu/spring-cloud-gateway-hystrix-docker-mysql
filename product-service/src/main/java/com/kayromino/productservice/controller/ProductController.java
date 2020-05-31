package com.kayromino.productservice.controller;

import com.kayromino.productservice.entity.Product;
import com.kayromino.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Product>  addProduct(@RequestBody Product product) {
        try {
            Optional<Product> productOptional = productService.addProduct(product);
            return productOptional.isPresent() ?
                    new ResponseEntity(productOptional.get(), HttpStatus.OK) :
                    new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Product>  updateProduct(@RequestBody Product product) {
        try {
            Optional<Product> productOptional = productService.updateProduct(product);
            return productOptional.isPresent() ?
                    new ResponseEntity(productOptional.get(), HttpStatus.OK) :
                    new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Optional<List<Product>>> getAllProducts() {
        try {
            Optional<List<Product>> optionalProductList = productService.getAllProducts();
            return optionalProductList.isPresent() ?
                    new ResponseEntity(optionalProductList.get(), HttpStatus.OK) :
                    new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
        try {
            Optional<Product> productOptional = productService.getProductById(id);
            return productOptional.isPresent() ?
                    new ResponseEntity(productOptional.get(), HttpStatus.OK) :
                    new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
