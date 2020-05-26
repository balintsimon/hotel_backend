package com.codecool.hotel_backend.service;

import com.codecool.hotel_backend.model.Room;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomStorage {
    private @Getter List<Room> roomStorage;
    private RoomCreator roomCreator;

    @Autowired
    public RoomStorage(RoomCreator roomCreator) {
        this.roomCreator = roomCreator;
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
