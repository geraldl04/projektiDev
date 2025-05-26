package com.fsemart.services.impl;

import com.fsemart.entity.Product;
import com.fsemart.repository.ProductRepository;
import com.fsemart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id){
        Optional<Product> produkti = productRepository.findById(id) ;
        if(produkti.isPresent()){
            return produkti.get();
        }
        return null;
    }


    @Override
    public Product save(Product product){
        return productRepository.save(product) ;
    }

    @Override
    public void delete(Product product){
        productRepository.delete(product);
    }

}
