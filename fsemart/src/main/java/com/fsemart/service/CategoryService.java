package com.fsemart.service;


import com.fsemart.entity.Category;
import com.fsemart.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {


    private  final CategoryRepository categoryRepository;


    public List<Category> findAllCategories(){
        return categoryRepository.findAll() ;
    }

    public Category findCategoryById(long id){
        Optional<Category> category = categoryRepository.findCategoryById(id);
        return category.orElse(null);
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }
    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findCategoryById(id);
        category.ifPresent(categoryRepository::delete);
    }
}
