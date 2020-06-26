package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.entity.Category;
import com.codecool.hotel_backend.repository.CategoryRepository;
import com.codecool.hotel_backend.service.RoomOrganiser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final RoomOrganiser roomOrganiser;
    private final ControllerUtil controllerUtil;

    @GetMapping("/{id}")
    public Category getAllByCategory(@PathVariable("id") Long id) {
        return categoryRepository.findCategoryById(id);
    }

    @GetMapping("") // According to rest specs this is the /all
    public List<Category> getAllCategory() {
        return categoryRepository.getCategoryStorage();
    }

    @RequestMapping("/get-available-categories-in-time-frame/{start}/{end}") // start end is for resource parameters, this needs request parameters ?=param
    public List<Category> getAvailableCategories(@PathVariable("start") String start, @PathVariable("end") String end) {
        return roomOrganiser.getAvailableCategoriesInTimeFrame(start, end);
    }
}
