package com.codecool.hotel_backend.controller;

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

//    // TODO: switch to H2
//    @RequestMapping(value = "/category/reserve/{id}", method = RequestMethod.POST)
//    public Room reserveARoomByCategoryId(@PathVariable("id") int id) {
//        return roomStorage.reserveARoom(id);
//    }

    @RequestMapping(value = "/category/reserve/{id}/{start}/{end}", method = RequestMethod.POST)
    public boolean reserveACategoryById(@PathVariable("id") Long id,
                                        @PathVariable("start") String start,
                                        @PathVariable("end") String end) {

        LocalDate startTime = LocalDate.parse(start);
        LocalDate endTime = LocalDate.parse(end);

        return true;
    }
}
