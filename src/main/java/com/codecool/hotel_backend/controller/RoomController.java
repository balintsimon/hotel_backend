package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.model.Room;
import com.codecool.hotel_backend.service.CategoryStorage;
import com.codecool.hotel_backend.service.RoomStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin(origins = {"http://localhost:8080"})
@RequestMapping("/room")
public class RoomController {
    RoomStorage roomStorage;

    @Autowired
    public RoomController(RoomStorage roomStorage, CategoryStorage categoryStorage ){
        this.roomStorage = roomStorage;
    }

    @GetMapping("/all")
    public List<Room> getAllRoom(){
        return roomStorage.getRoomStorage();
    }

}
