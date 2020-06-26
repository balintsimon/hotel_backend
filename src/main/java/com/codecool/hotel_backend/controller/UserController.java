package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.entity.HotelUser;
import com.codecool.hotel_backend.entity.Reservation;
import com.codecool.hotel_backend.repository.ReservationRepository;
import com.codecool.hotel_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class UserController {

    private UserRepository userRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @GetMapping("/userByReservation/{reservationId}") // /reservation/user/1
    public HotelUser getAllRooms(@PathVariable("reservationId") Long reservationId) {
        Reservation reservation= reservationRepository.findReservationById(reservationId);
        return userRepository.getHotelUserByReservations(reservation);
    }


}
