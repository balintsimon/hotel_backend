package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.entity.Reservation;
import com.codecool.hotel_backend.entity.Room;
import com.codecool.hotel_backend.repository.CategoryRepository;
import com.codecool.hotel_backend.repository.ReservationRepository;
import com.codecool.hotel_backend.service.RoomOrganizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
public class ReservationController {

    ReservationRepository reservationRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public ReservationController(CategoryRepository categoryRepository, ReservationRepository reservationRepository) {
        this.categoryRepository = categoryRepository;
        this.reservationRepository = reservationRepository;
    }

    @Autowired
    RoomOrganizer roomOrganizer;


    @RequestMapping(value = "/test/{start}/{end}", method = RequestMethod.GET)
    public List<Room> getAvailableRooms(                   @PathVariable("start") String start,
                                                           @PathVariable("end") String end) {
        roomOrganizer.getAvailableRooms(start, end);

        return null;
    }

    @RequestMapping(value = "/test/{start}/{end}/{id}", method = RequestMethod.GET)
    public List<Room> getAvailableRooms(@PathVariable("id") Long id,
                                        @PathVariable("start") String start,
                                        @PathVariable("end") String end) {
        roomOrganizer.getAvailableRoomsInCategory(start, end, id);

        return null;
    }


//    @RequestMapping(value = "/category/available/{id}/{start}/{end}", method = RequestMethod.POST)
//    public boolean checkIfCategoryAvailableInTimeFrameById(@PathVariable("id") Long id,
//                                                           @PathVariable("start") String start,
//                                                           @PathVariable("end") String end) {
//        LocalDate startDate = LocalDate.parse(start);
//        LocalDate endDate = LocalDate.parse(end);
//
//        List<Reservation> allRoomsOfType = reservationRepository.getAllById(id);
//        List<Reservation> takenRooms = reservationRepository.getAvailableReservations(startDate, endDate);
//
//        return takenRooms.size() < allRoomsOfType.size();
//    }

//    @RequestMapping(value = "/category/reserve/{id}/{start}/{end}", method = RequestMethod.POST)
//    public boolean reserveACategoryById(@PathVariable("id") Long id,
//                                        @PathVariable("start") String start,
//                                        @PathVariable("end") String end) {
//
//        LocalDate startDate = LocalDate.parse(start);
//        LocalDate endDate = LocalDate.parse(end);
//
//        if (checkIfCategoryAvailableInTimeFrameById(id, start, end)) {
//            return saveNewReservation(id, startDate, endDate);
//        }
//        return false;
//    }

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
