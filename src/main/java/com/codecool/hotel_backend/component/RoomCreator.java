package com.codecool.hotel_backend.component;

import com.codecool.hotel_backend.model.Luxury;
import com.codecool.hotel_backend.model.RockstarSuite;
import com.codecool.hotel_backend.model.Room;
import com.codecool.hotel_backend.model.SuperiorStreetView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomCreator {

    public List<Room> createRooms(){
        ArrayList<Room> roomStorage = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            roomStorage.add(new Room(new Luxury()));
            roomStorage.add(new Room(new SuperiorStreetView()));
            roomStorage.add(new Room(new RockstarSuite()));
        }


        return roomStorage;
    }
}
