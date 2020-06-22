package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.entity.Category;
import com.codecool.hotel_backend.repository.CategoryRepository;
import com.codecool.hotel_backend.service.RoomOrganiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryRepository categoryRepository;
    RoomOrganiser roomOrganiser;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository, RoomOrganiser roomOrganiser) {
        this.categoryRepository = categoryRepository;
        this.roomOrganiser = roomOrganiser;
    }

    @GetMapping("/{id}")
    public Category getAllByCategory(@PathVariable("id") Long id) {
        return categoryRepository.findCategoryById(id);
    }

    @GetMapping("/all")
    public List<Category> getAllCategory() {
        return categoryRepository.getCategoryStorage();
    }

    @RequestMapping("/get-available-categories-in-time-frame/{start}/{end}")
    public List<Category> getAvailableCategories(@PathVariable("start") String start, @PathVariable("end") String end) {
        return roomOrganiser.getAvailableCategoriesInTimeFrame(start, end);
    }
}
