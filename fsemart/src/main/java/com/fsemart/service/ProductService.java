package com.fsemart.service;

import com.fsemart.entity.Product;

import java.util.List;

public interface ProductService {

         List<Product> findAll() ;

       Product findProductById(Long id);

       Product save(Product product) ;

        void delete(Product product) ;

         List<Product> gjejProdukte(String emri);

         List<Product> gjejProdukteShtuarSeFundmi();

         long  gjejNrEProdukteveNeBazeTeKategorise(Long id);

}
