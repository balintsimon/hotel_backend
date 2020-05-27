package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.model.Room;
import com.codecool.hotel_backend.service.CategoryStorage;
import com.codecool.hotel_backend.service.RoomStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
//@CrossOrigin(origins = {"http://localhost:8080"})
@RequestMapping("/room")
public class RoomController {
    RoomStorage roomStorage;
    CategoryStorage categoryStorage;

    @Autowired
    public RoomController(RoomStorage roomStorage, CategoryStorage categoryStorage ){
        this.roomStorage = roomStorage;
        this.categoryStorage=categoryStorage;

    }

    @GetMapping("/all")
    public List<Room> getAllRoom(){
        return roomStorage.getRoomStorage();
    }

    @GetMapping("/numberOfAvailableRoomsByCategory")
    public HashMap<Integer, Integer> getNumberOfAvailableRoomsByCategory(){
        return categoryStorage.getNumberOfAvailableRoomsByCategory(roomStorage.getAllAvailableRooms());
    }

    @GetMapping("/numberOfAvailableRoomsByCategory/{id}")
    public HashMap<Integer, Integer> getNumberOfAvailableRoomsByCategoryId(@PathVariable("id") int id){
        return categoryStorage.getNumberOfAvailableRoomsByCategoryId(roomStorage.getAllAvailableRooms(),id);
    }


}
