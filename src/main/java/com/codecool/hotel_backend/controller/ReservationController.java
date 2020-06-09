package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.entity.Reservation;
import com.codecool.hotel_backend.model.Room;
import com.codecool.hotel_backend.repository.CategoryRepository;
import com.codecool.hotel_backend.repository.ReservationRepository;
import com.codecool.hotel_backend.service.RoomStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class ReservationController {

    ReservationRepository reservationRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public ReservationController(CategoryRepository categoryRepository, ReservationRepository reservationRepository) {
        this.categoryRepository = categoryRepository;
        this.reservationRepository = reservationRepository;
    }

    @RequestMapping(value = "/category/reserve/{id}/{start}/{end}", method = RequestMethod.POST)
    public boolean reserveACategoryById(@PathVariable("id") Long id,
                                        @PathVariable("start") String start,
                                        @PathVariable("end") String end) {

        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        return saveNewReservation(id, startDate, endDate);
    }


    private boolean saveNewReservation(Long categoryId, LocalDate startDate, LocalDate endDate) {
        Reservation reservation = Reservation.builder()
                .category(categoryRepository.findCategoryById(categoryId))
                .startDate(startDate)
                .endDate(endDate)
                .build();

        reservationRepository.saveAndFlush(reservation);
        return true;

    }
}
