package com.codecool.hotel_backend.component;

import com.codecool.hotel_backend.model.Category;
import com.codecool.hotel_backend.model.Luxury;
import com.codecool.hotel_backend.model.RockstarSuite;
import com.codecool.hotel_backend.model.SuperiorStreetView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryCreator {

    public List<Category> createCategories(Luxury luxury,SuperiorStreetView superiorStreetView,RockstarSuite rockstarSuite){
        ArrayList<Category> categoryStorage = new ArrayList<>();
        categoryStorage.add(luxury);
        categoryStorage.add(superiorStreetView);
        categoryStorage.add(rockstarSuite);
        return categoryStorage;
    }
}
