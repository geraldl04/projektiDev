package com.fsemart.services.impl;


import com.fsemart.entity.Category;
import com.fsemart.repository.CategoryRepository;
import com.fsemart.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;


    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(long id) {
        Optional<Category> category = categoryRepository.findCategoryById(id);
        return category.orElse(null);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findCategoryById(id);
        category.ifPresent(categoryRepository::delete);
    }
}
