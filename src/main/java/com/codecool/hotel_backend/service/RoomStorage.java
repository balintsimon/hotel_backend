package com.codecool.hotel_backend.service;

import com.codecool.hotel_backend.component.RoomCreator;
import com.codecool.hotel_backend.model.Category;
import com.codecool.hotel_backend.model.Room;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
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

    public List<Room> getAllAvailableRooms(){
        List<Room> resultList = new ArrayList<>();
        for (Room room : roomStorage){
            if(room.isOccupied()==false){
                resultList.add(room);
            }
        }
        return resultList;
    }

    public void reserveARoom(int id){
        for (Room room:roomStorage){
            if (room.getCategory().getId() == id){
                if (!room.isOccupied()){
                    room.setOccupied(true);
                    break;
                }
            }
        }
    }




//    public List<Room> getRoomsByCategory(String category){
//        List<Room> resultList = new ArrayList<>();
//        for (Room room: roomStorage){
//            if (room.getClass().getName().toLowerCase().equals(category.toLowerCase())){
//                resultList.add(room);
//            }
//        }
//        return resultList;
//    }
}
