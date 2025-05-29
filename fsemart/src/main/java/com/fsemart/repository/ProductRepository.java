package com.fsemart.repository;

import com.fsemart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductById(Long id);

    //Query i cili eshte i gatshem si "funksion" kerkon per ate substring title brenda titlet
    List<Product> findByTitleContainingIgnoreCase(String title);

    List<Product> findTop3ByOrderByCreatedAtDesc();

    long countByCategoryId(Long categoryId);



}
