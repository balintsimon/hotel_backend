package com.codecool.hotel_backend.service;

import com.codecool.hotel_backend.repository.CategoryRepository;
import com.codecool.hotel_backend.repository.ReservationRepository;
import com.codecool.hotel_backend.repository.ReservedRoomRepository;
import com.codecool.hotel_backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RoomOrganizer {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservedRoomRepository reservedRoomRepository;
    @Autowired
    RoomRepository roomRepository;



}
