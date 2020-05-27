package com.codecool.hotel_backend.service;

import com.codecool.hotel_backend.component.CategoryCreator;
import com.codecool.hotel_backend.model.Category;
import com.codecool.hotel_backend.model.Room;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public HashMap<Integer,Integer> getNumberOfAvailableRoomsByCategory(List<Room> roomList){
        HashMap<Integer,Integer> resultMap=new HashMap<>();
        for (int i=0; i<categoryStorage.size(); i++){
            int counter=0;
            for(Room room:roomList){
                if(room.getCategory().getId()==i){
                    counter++;
                }

            }
            resultMap.put(i,counter);
        }
        return resultMap;
    }
    public HashMap<Integer,Integer> getNumberOfAvailableRoomsByCategoryId(List<Room> roomList,int id){
        HashMap<Integer,Integer> resultMap=new HashMap<>();
            int counter=0;
            for(Room room:roomList){
                if(room.getCategory().getId()==id){
                    counter++;
                }
            }
            resultMap.put(id,counter);

        return resultMap;
    }
}
