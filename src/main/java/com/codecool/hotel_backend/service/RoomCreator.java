package com.codecool.hotel_backend.service;

import com.codecool.hotel_backend.model.room.Luxury;
import com.codecool.hotel_backend.model.room.RockstarSuite;
import com.codecool.hotel_backend.model.room.Room;
import com.codecool.hotel_backend.model.room.SuperiorStreetView;
import java.util.ArrayList;
import java.util.List;

public class RoomCreator {

    public List<Room> createRooms(){
        ArrayList<Room> roomStorage = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            roomStorage.add(new Luxury());
            roomStorage.add(new SuperiorStreetView());
            roomStorage.add(new RockstarSuite());
        }


        return roomStorage;
    }
}
