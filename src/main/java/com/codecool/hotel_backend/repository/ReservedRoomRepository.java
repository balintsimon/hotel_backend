package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.ReservedRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservedRoomRepository extends JpaRepository<ReservedRoom, Long> {

    // This is utterly idiotic but works
    @Query("select res, roo, reserv from ReservedRoom res join res.room roo join res.reservation reserv")
    List<ReservedRoom> findAll();
}
