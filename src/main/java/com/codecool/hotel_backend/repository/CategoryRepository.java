package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryById(Long Id);

    @Query(value = "SELECT c FROM Category c")
    List<Category> getCategoryStorage();
    
}
