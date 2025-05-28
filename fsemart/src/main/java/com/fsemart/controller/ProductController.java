package com.fsemart.controller;


import com.fsemart.entity.Product;
import com.fsemart.entity.ProductAdminDto;
import com.fsemart.entity.ProductUserDto;
import com.fsemart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    //kthen nje liste produktesh por
    //perdoret ? wildcard per te treguar se tipi i kthyaer mund te jete ose productAdminDto ose userDto
    //ne menyre qe useri te mos shikoje kush eshte seller
    public List<?> getAllProducts() {
        //merr username rolet , detajet e autentikimit
        var auth = SecurityContextHolder.getContext().getAuthentication();

        //meqe getAuthorities esht collection , stream me kthen nje sekuence stremashes me ate element
        //e perdor stream ne collection qe te eprdor metoda per te kapur elementet
        //a-> lambda expression qe per cdo aurotitet a kontrollon a e ka rolin admin
        boolean isAdmin = auth.getAuthorities().stream().anyMatch
                (a -> a.getAuthority().equals("ROLE_ADMIN"));

        List<Product> products = productService.findAll();
        //e njejta logjike . stream per te marre cdo produkt dhe .map per ta transformu produktin ne dto
        //pra .map perdoret per te marr cdo element ne stream dhe per ta kthyer ne dicka tjeter
        //qe do thote per cdo produkt p krijo nje produkt admin dto duke perdorur elementet e p
        if (isAdmin) {
            return products
                    //ktu pastaj i marr me get te dhenat
                    .stream().map(p -> new ProductAdminDto(
                            p.getId(),
                            p.getTitle(),
                            p.getDescription(),
                            p.getPrice(),
                            p.getStock(),
                            p.getImageUrl(),
                            p.getCategory() != null ? p.getCategory().getId() : null,
                            p.getSeller() != null ? p.getSeller().getUsername() : null,
                            p.getSeller() != null ? p.getSeller().getEmail() : null
                    ))
                    .toList();
        } else {
            return products
                    //ktu pastaj i marr njesoj por heq sellerin
                    .stream().map(p -> new ProductUserDto(
                            p.getId(),
                            p.getTitle(),
                            p.getDescription(),
                            p.getPrice(),
                            p.getStock(),
                            p.getImageUrl(),
                            p.getCategory() != null ? p.getCategory().getId() : null

                    ))
                    .toList();
        }


    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public Object getProductById(@PathVariable Long id) {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

       Product product  = productService.findProductById(id);
  //pa i bere kastin Product me jepte error se me kthen object metoda , ose ndryshoj te me ktheje objekt
        //ose i bej cast
 //me jep gabim kompilimi se po tentoj te kthej ne Product nje liste admindto soe userdto
        //kshuqe menyra do jete te kthej ne metode Object dhe jo sic ishte Product
//        return (Product) products.stream().map(p -> ktheNeDto(p , isAdmin))
//                .toList();

        return ktheNeDto(product, isAdmin);
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



    //meqe kam dhe getAllProducts dhe get productById , te krijoj nje metode qe te mos kem duplikim kodi
    //getAllproducts po e lej ashtu per demonstrim , nuk po e therras kete metode
    private Object ktheNeDto(Product p, boolean isAdmin) {
        if (isAdmin) {
            return new ProductAdminDto(
                    p.getId(),
                    p.getTitle(),
                    p.getDescription(),
                    p.getPrice(),
                    p.getStock(),
                    p.getImageUrl(),
                    p.getCategory() != null ? p.getCategory().getId() : null,
                    p.getSeller() != null ? p.getSeller().getUsername() : null,
                    p.getSeller() != null ? p.getSeller().getEmail() : null
            );
        } else {
            return new ProductUserDto(
                    p.getId(),
                    p.getTitle(),
                    p.getDescription(),
                    p.getPrice(),
                    p.getStock(),
                    p.getImageUrl(),
                    p.getCategory() != null ? p.getCategory().getId() : null
            );
        }
    }
}
