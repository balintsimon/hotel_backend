package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.entity.Room;
import com.codecool.hotel_backend.repository.RoomRepository;
import com.codecool.hotel_backend.service.CategoryStorage;
import com.codecool.hotel_backend.service.RoomStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins = {"http://localhost:8080"})
@RequestMapping("/room")
public class RoomController {
    RoomStorage roomStorage;
    CategoryStorage categoryStorage;
    RoomRepository roomRepository;

    @Autowired
    public RoomController(RoomStorage roomStorage, CategoryStorage categoryStorage, RoomRepository roomRepository){
        this.roomStorage = roomStorage;
        this.categoryStorage=categoryStorage;
        this.roomRepository = roomRepository;
    }

    @GetMapping("/all")
    public List<Room> getAllRoom(){
        return roomRepository.getRoomStorage();
    }

    // TODO: to H2
    @GetMapping("/numberOfAvailableRoomsByCategory")
    public HashMap<Integer, Integer> getNumberOfAvailableRoomsByCategory(){
        return categoryStorage.getNumberOfAvailableRoomsByCategory(roomStorage.getAllAvailableRooms());
    }

    // TODO: to H2
    @GetMapping("/numberOfAvailableRoomsByCategory/{id}")
    public HashMap<Integer, Integer> getNumberOfAvailableRoomsByCategoryId(@PathVariable("id") int id){
        return categoryStorage.getNumberOfAvailableRoomsByCategoryId(roomStorage.getAllAvailableRooms(),id);
    }

    // TODO: to H2
//    @GetMapping("/allOccupiedRooms")
//    public List<Room> getAllOccupiedRooms(){
//        return roomStorage.getAllOccupiedRooms();
//    }


}
