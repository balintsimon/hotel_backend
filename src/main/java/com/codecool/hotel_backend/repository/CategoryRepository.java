package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByCategoryId(Long categoryId);

//    @Query(value = "SELECT DISTINCT c FROM Category c WHERE Category.categoryId = :id")
//    Category getCategoryOnId(@Param("id") Long id);

    @Query(value = "SELECT c FROM Category c")
    List<Category> getCategoryStorage();
    
}
