package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.entity.Category;
import com.codecool.hotel_backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/{id}")
    public Category getAllByCategory(@PathVariable("id") Long id) {
        return categoryRepository.findCategoryById(id);
    }

    @GetMapping("/all")
    public List<Category> getAllCategory() {
        return categoryRepository.getCategoryStorage();
    }
}
