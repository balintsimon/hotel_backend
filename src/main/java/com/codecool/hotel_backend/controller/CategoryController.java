package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.entity.Category;
import com.codecool.hotel_backend.model.Room;
import com.codecool.hotel_backend.repository.CategoryRepository;
import com.codecool.hotel_backend.service.RoomStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    RoomStorage roomStorage; // TODO: switch to H2
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository, RoomStorage roomStorage) {
        this.categoryRepository = categoryRepository;
        this.roomStorage = roomStorage;
    }

    @GetMapping("/{id}")
    public Category getAllByCategory(@PathVariable("id") Long id) {
        return categoryRepository.findCategoryByCategoryId(id);
    }

    @GetMapping("/all")
    public List<Category> getAllCategory() {
        return categoryRepository.getCategoryStorage();
    }

    // TODO: switch to H2
    @RequestMapping(value = "/reserve/{id}", method = RequestMethod.POST)
    public Room reserveARoomByCategoryId(@PathVariable("id") int id) {
        return roomStorage.reserveARoom(id);
    }
}
