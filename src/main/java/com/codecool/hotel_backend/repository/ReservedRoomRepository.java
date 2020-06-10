package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.ReservedRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservedRoomRepository extends JpaRepository<ReservedRoom, Long> {

    @Query("select reserve, room, reserved from ReservedRoom reserve join reserve.room room join reserve.reservation reserved")
    List<ReservedRoom> findAll();
}
