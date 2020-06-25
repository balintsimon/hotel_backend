package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.entity.HotelUser;
import com.codecool.hotel_backend.entity.Reservation;
import com.codecool.hotel_backend.entity.Room;
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

    @GetMapping("/userByReservation/{reservationId}")
    public HotelUser getAllRooms(@PathVariable("reservationId") long reservationId) {
        Reservation reservation= reservationRepository.findReservationById(reservationId);
        HotelUser hotelUser=userRepository.getHotelUserByReservations(reservation);
        return hotelUser;
    }


}
