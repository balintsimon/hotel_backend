package com.codecool.hotel_backend;

import com.codecool.hotel_backend.component.RoomCreator;
import com.codecool.hotel_backend.model.Category;
import com.codecool.hotel_backend.model.Luxury;
import com.codecool.hotel_backend.model.RockstarSuite;
import com.codecool.hotel_backend.model.Room;
import com.codecool.hotel_backend.service.CategoryStorage;
import com.codecool.hotel_backend.service.RoomStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class HotelBackendApplicationTests {

    @Autowired
    private RoomStorage roomStorage;
    @Autowired
    private CategoryStorage categoryStorage;

    @Test
    void getAllAvailableRoomsTestSize() {
        List<Room> roomList=new ArrayList<>();
        Room room1=mock(Room.class);
        roomList.add(room1);
        roomList.add(room1);

        RoomCreator roomCreator1=mock(RoomCreator.class);
        when(roomCreator1.createRooms()).thenReturn(roomList);
        RoomStorage roomStorage=new RoomStorage(roomCreator1);

        assertEquals(roomStorage.getAllAvailableRooms().size(),2);
    }

    @Test
    void getAllOccupiedRoomsTest(){

        List<Room> roomList=new ArrayList<>();
        Category category=mock(Category.class);
        Room room1=mock(Room.class);
        Room room2=new Room(category);
        room2.setOccupied(true);
        roomList.add(room1);
        roomList.add(room2);

        RoomCreator roomCreator1=mock(RoomCreator.class);
        when(roomCreator1.createRooms()).thenReturn(roomList);
        RoomStorage roomStorage=new RoomStorage(roomCreator1);

        assertEquals(roomStorage.getAllOccupiedRooms().size(),1);
    }

    @Test
    void reserveARoomTest(){
        List<Room> roomList=new ArrayList<>();
        Category category=new Luxury(); //getter
        Room room1=new Room(category); //getter
        roomList.add(room1);

        RoomCreator roomCreator1=mock(RoomCreator.class);
        when(roomCreator1.createRooms()).thenReturn(roomList);
        RoomStorage roomStorage=new RoomStorage(roomCreator1);

        roomStorage.reserveARoom(1);

        assertEquals(roomStorage.getAllOccupiedRooms().size(),1);

    }


    @Test
    void getCategoryByIdTestForValid(){
        assertEquals(categoryStorage.getCategoryById(1).toString(), new Luxury().toString());
    }




}
