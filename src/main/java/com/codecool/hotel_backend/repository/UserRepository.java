package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.HotelUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<HotelUser, Long> {

    Optional<Object> findByUsername(String username);

    HotelUser getHotelUserByUsername(String username);
}
