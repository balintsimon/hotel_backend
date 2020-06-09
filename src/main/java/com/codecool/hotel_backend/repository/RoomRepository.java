package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.Category;
import com.codecool.hotel_backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "SELECT r FROM Room r")
    Room[] getRoomStorage();
}
