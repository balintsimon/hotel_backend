package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.ReservedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedRoomRepository extends JpaRepository<ReservedRoom, Long> {
}
