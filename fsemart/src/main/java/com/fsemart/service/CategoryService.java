package com.fsemart.service;

import com.fsemart.entity.Category;

import java.util.List;

public interface CategoryService {


     List<Category> findAllCategories();

    Category findCategoryById(long id);

    Category saveCategory(Category category);

    void deleteCategory(Long id);

}
