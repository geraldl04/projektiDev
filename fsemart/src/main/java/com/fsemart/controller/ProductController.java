package com.fsemart.controller;


import com.fsemart.entity.Product;
import com.fsemart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {


    @Autowired
    private final ProductService productService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product oldProduct = productService.findProductById(id) ;

        oldProduct.setTitle(product.getTitle());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setStock(product.getStock());
        oldProduct.setImageUrl(product.getImageUrl());
        oldProduct.setCategory(product.getCategory());
        oldProduct.setSeller(product.getSeller());

        return productService.save(oldProduct);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
         return productService.save(product);
    }
}
