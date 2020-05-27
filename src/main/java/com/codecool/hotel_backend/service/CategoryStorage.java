package com.codecool.hotel_backend.service;

import com.codecool.hotel_backend.component.CategoryCreator;
import com.codecool.hotel_backend.model.Category;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryStorage {
    private @Getter List<Category> categoryStorage;
    private CategoryCreator categoryCreator;

    @Autowired
    public CategoryStorage(CategoryCreator categoryCreator) {
        this.categoryCreator = categoryCreator;
        categoryStorage = categoryCreator.createCategories();
    }

    public Category getCategoryById(int id){
        for (Category category: categoryStorage){
            if (category.getId() == id){
                return category;
            }
        }
        return null;
    }
}
