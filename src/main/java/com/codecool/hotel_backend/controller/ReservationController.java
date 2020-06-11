package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.entity.Reservation;
import com.codecool.hotel_backend.entity.ReservedRoom;
import com.codecool.hotel_backend.repository.CategoryRepository;
import com.codecool.hotel_backend.repository.ReservationRepository;
import com.codecool.hotel_backend.repository.ReservedRoomRepository;
import com.codecool.hotel_backend.service.RoomOrganiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ReservationController {

    ReservationRepository reservationRepository;
    CategoryRepository categoryRepository;
    RoomOrganiser roomOrganiser;
    ReservedRoomRepository reservedRoomRepository;

    @Autowired
    public ReservationController(CategoryRepository categoryRepository, ReservationRepository reservationRepository,
                                 RoomOrganiser roomOrganiser, ReservedRoomRepository reservedRoomRepository) {
        this.categoryRepository = categoryRepository;
        this.reservationRepository = reservationRepository;
        this.roomOrganiser = roomOrganiser;
        this.reservedRoomRepository = reservedRoomRepository;
    }

    @RequestMapping("/get-reserved-and-reservation-joined")
    public List<Reservation> test() {return reservationRepository.getAllReservationJoin();}

    @RequestMapping(value = "/finalise_reservation/{res_id}/{room_id}/{start}/{end}", method = RequestMethod.POST)
    public boolean finaliseReservation(@PathVariable("res_id") Long res_id,
                                       @PathVariable("room_id") Long room_id,
                                       @PathVariable("start") String start,
                                       @PathVariable("end") String end) {
        try {
            roomOrganiser.finaliseReservation(res_id, room_id, start, end);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping(value = "/get-all-reservations")
    public List<Reservation> getAllReservations() {
        return roomOrganiser.getAllReservations();
    }

    @RequestMapping(value = "/get-all-reserved-rooms")
    public List<ReservedRoom> getAllReservedRooms() {
        return reservedRoomRepository.findAll();
    }

    @RequestMapping(value = "/category/reserve/{category_id}/{start}/{end}", method = RequestMethod.POST)
    public boolean reserveRoom(@PathVariable("category_id") Long id,
                               @PathVariable("start") String start,
                               @PathVariable("end") String end) {
        return roomOrganiser.reserveRoom(id, start, end);
    }

    @RequestMapping(value = "/category/available/{id}/{start}/{end}", method = RequestMethod.POST)
    public boolean checkIfCategoryAvailableInTimeFrameById(@PathVariable("id") Long id,
                                                           @PathVariable("start") String start,
                                                           @PathVariable("end") String end) {
        return roomOrganiser.getAvailableRoomsInCategory(start, end, id).size() > 0;
    }
}
