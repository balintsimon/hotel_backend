package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.HotelUser;
import com.codecool.hotel_backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.imageio.spi.RegisterableService;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<HotelUser, Long> {

    Optional<HotelUser> findByUsername(String username);

    HotelUser getHotelUserByUsername(String username);

    List<HotelUser> findAll();

    HotelUser getHotelUserByReservations(Reservation reservation);
}
