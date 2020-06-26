package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.Category;
import com.codecool.hotel_backend.entity.ReservedRoom;
import com.codecool.hotel_backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

//    @Query(value = "SELECT r FROM Room r")
//    Room[] getRoomStorage();
    @Query(value = "SELECT r.id, r.category, r.reservedRoomList FROM Room r")
    List<Room> getRoomStorage();

    Room findRoomById(Long id);

    List<Room> findAll();

    List<Room> findAllByCategory_Id(Long id);
}
