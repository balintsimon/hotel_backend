package com.codecool.hotel_backend.hotel_backend.controller;

import com.codecool.hotel_backend.hotel_backend.model.room.Room;
import com.codecool.hotel_backend.hotel_backend.service.RoomStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = {"http://localhost:8080"})
@RequestMapping("/rooms")
public class RoomController {
    RoomStorage roomStorage;

    @Autowired
    public RoomController(RoomStorage roomStorage){
        this.roomStorage = roomStorage;
    }

    @GetMapping("/all")
    public List<Room> getAllRoom(){
        return roomStorage.getRoomStorage();
    }

    @GetMapping("/category/{category}")
    public List<Room> getAllByCategory(@PathVariable("category") String category){
        return roomStorage.getRoomsByCategory(category);
    }
}
