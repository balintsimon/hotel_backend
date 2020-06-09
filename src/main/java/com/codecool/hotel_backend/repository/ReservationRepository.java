package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
