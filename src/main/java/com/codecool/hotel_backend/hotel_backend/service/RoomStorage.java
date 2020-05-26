package com.codecool.hotel_backend.hotel_backend.service;

import com.codecool.hotel_backend.hotel_backend.model.room.Room;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomStorage {
    private @Getter List<Room> roomStorage;

    public RoomStorage() {
        RoomCreator roomCreator = new RoomCreator();
        roomStorage = roomCreator.createRooms();
    }

    public List<Room> getRoomsByCategory(String category){
        List<Room> resultList = new ArrayList<>();
        for (Room room: roomStorage){
            if (room.getCategory().toLowerCase().equals(category.toLowerCase())){
                resultList.add(room);
            }
        }
        return resultList;
    }

}
