package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.model.Category;
import com.codecool.hotel_backend.service.CategoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryStorage categoryStorage;

    @Autowired
    public CategoryController(CategoryStorage categoryStorage){
        this.categoryStorage = categoryStorage;
    }

    @GetMapping("/{id}")
    public Category getAllByCategory(@PathVariable("id") int id){
        return categoryStorage.getCategoryById(id);
    }

    @GetMapping("/all")
    public List<Category> getAllCategory(){
        return categoryStorage.getCategoryStorage();
    }
}
