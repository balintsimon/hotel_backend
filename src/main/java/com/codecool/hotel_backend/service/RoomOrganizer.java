package com.codecool.hotel_backend.service;

import com.codecool.hotel_backend.entity.Reservation;
import com.codecool.hotel_backend.entity.Room;
import com.codecool.hotel_backend.repository.CategoryRepository;
import com.codecool.hotel_backend.repository.ReservationRepository;
import com.codecool.hotel_backend.repository.ReservedRoomRepository;
import com.codecool.hotel_backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Component
public class RoomOrganizer {

    private ReservationRepository reservationRepository;

    @Autowired
    public RoomOrganizer(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Room> getAvailableRooms(String start, String end) {
        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(start);
            endDate = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        List<Reservation> reservations = reservationRepository.findAll();
//        System.out.println(reservations);

        for (Reservation reservation : reservations) {

        }


        return null;
    }

}
