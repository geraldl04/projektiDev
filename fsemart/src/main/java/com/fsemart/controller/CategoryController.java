
package com.fsemart.controller;



import com.fsemart.entity.*;
import com.fsemart.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<?> getAllCategories() {
        //shiko metoden ne produkt per ta kuptuar se eshte implementuar me para aty
        var auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch
                (a -> a.getAuthority().equals("ROLE_ADMIN"));

//e njejta logjike do marr gjitha kategorite , i bej stream ti marr vec e vec , i bej
        //map qe ti kthej nga kategori ne CategoryAdminDto ose CategoryUserDto
        return categoryService.findAllCategories().stream().map(
                kategori -> ktheKategoriNeDto(kategori , isAdmin)).toList() ;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public Object getCategoryById(@PathVariable Long id) {

        var auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch
                (a -> a.getAuthority().equals("ROLE_ADMIN"));

       Category category = categoryService.findCategoryById(id) ;

       return  ktheKategoriNeDto(category, isAdmin);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

      private Object ktheKategoriNeDto(Category category , boolean isAdmin){
        if(!isAdmin){

            List<ProductUserDto> produktetUser  = category.getProducts().stream().map(
                    p -> new ProductUserDto(
                            p.getId(),
                            p.getTitle(),
                            p.getDescription(),
                            p.getPrice(),
                            p.getStock(),
                            p.getImageUrl(),
                            p.getCategory() != null ? p.getCategory().getId() : null
                    )).toList();

            CategoryUserDto categoryUserDto = new CategoryUserDto(
                    category.getId(), category.getName(), produktetUser
            );

            return  categoryUserDto;

        }else {

            List<ProductAdminDto> produktetAdmin = category.getProducts().stream().map(
                    p -> new ProductAdminDto(
                            p.getId(),
                            p.getTitle(),
                            p.getDescription(),
                            p.getPrice(),
                            p.getStock(),
                            p.getImageUrl(),
                            p.getCategory() != null ? p.getCategory().getId() : null,
                            p.getSeller() != null ? p.getSeller().getUsername() : null,
                            p.getSeller() != null ? p.getSeller().getEmail() : null
            )).toList();

            CategoryAdminDto CategoryAdminDto = new CategoryAdminDto(
                    category.getId(), category.getName(), produktetAdmin
            );

            return CategoryAdminDto;
        }
      }


}