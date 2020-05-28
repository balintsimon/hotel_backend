package com.codecool.hotel_backend;

import com.codecool.hotel_backend.model.Category;
import com.codecool.hotel_backend.model.Luxury;
import com.codecool.hotel_backend.model.Room;
import com.codecool.hotel_backend.service.CategoryStorage;
import com.codecool.hotel_backend.service.RoomStorage;
import org.junit.jupiter.api.Test;
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

    //TESTS WITH MOCKING
    @Test
    void getAllAvailableRoomsMockingTest() {
        RoomStorage roomStorage=mock(RoomStorage.class);
        Category category=mock(Category.class);

        List<Room> roomList=mock(ArrayList.class);
        roomList.add(new Room(category));

        when(roomStorage.getAllAvailableRooms()).thenReturn(roomList);

        assertEquals(roomStorage.getAllAvailableRooms(),roomList);
    }


    //TESTS WITHOUT MOCKING
    @Test
    void getAllAvailableRoomsTest(){
        assertEquals(roomStorage.getAllAvailableRooms().size(),30);
    }

    @Test
    void getAllOccupiedRoomsTestForZero(){
        assertEquals(roomStorage.getAllOccupiedRooms().size(),0);
    }

    @Test
    void getAllOccupiedRoomsTestForOne(){
        roomStorage.reserveARoom(1);
        assertEquals(roomStorage.getAllOccupiedRooms().size(),1);
    }


    @Test
    void getCategoryByIdTestForValid(){
        assertEquals(categoryStorage.getCategoryById(1).toString(), new Luxury().toString());
    }




}
