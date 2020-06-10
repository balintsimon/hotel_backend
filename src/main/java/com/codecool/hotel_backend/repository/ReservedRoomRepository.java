package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.ReservedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservedRoomRepository extends JpaRepository<ReservedRoom, Long> {

    List<ReservedRoom> findAll();
}
