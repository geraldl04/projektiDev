package com.fsemart.repository;

import com.fsemart.entity.Category;
import com.fsemart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer> {
    Optional<Category> findCategoryById(Long id);

}
