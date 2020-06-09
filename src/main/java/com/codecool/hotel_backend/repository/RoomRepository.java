package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
