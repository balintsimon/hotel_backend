package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.model.Category;
import com.codecool.hotel_backend.service.CategoryStorage;
import com.codecool.hotel_backend.service.RoomStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryStorage categoryStorage;
    RoomStorage roomStorage;

    @Autowired
    public CategoryController(CategoryStorage categoryStorage,RoomStorage roomStorage){
        this.categoryStorage = categoryStorage;
        this.roomStorage = roomStorage;
    }

    @GetMapping("/{id}")
    public Category getAllByCategory(@PathVariable("id") int id){
        return categoryStorage.getCategoryById(id);
    }

    @GetMapping("/all")
    public List<Category> getAllCategory(){
        return categoryStorage.getCategoryStorage();
    }

    @RequestMapping(value = "/reserve/{id}", method = RequestMethod.POST)
    public void reserveARoomByCategoryId(@PathVariable("id") int id){
        roomStorage.reserveARoom(id);
    }
}
