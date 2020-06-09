package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.entity.Category;
import com.codecool.hotel_backend.entity.Reservation;
import com.codecool.hotel_backend.entity.ReservedRoom;
import com.codecool.hotel_backend.entity.Room;
import com.codecool.hotel_backend.repository.ReservationRepository;
import com.codecool.hotel_backend.repository.ReservedRoomRepository;
import com.codecool.hotel_backend.repository.RoomRepository;
import com.codecool.hotel_backend.service.CategoryStorage;
import com.codecool.hotel_backend.service.RoomStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins = {"http://localhost:8080"})
@RequestMapping("/room")
public class RoomController {
    RoomRepository roomRepository;
    ReservationRepository reservationRepository;
    ReservedRoomRepository reservedRoomRepository;

    @Autowired
    public RoomController(RoomRepository roomRepository, ReservedRoomRepository reservedRoomRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservedRoomRepository = reservedRoomRepository;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/all")
    public List<Room> getAllRoom() {
        return roomRepository.getRoomStorage();
    }

    @GetMapping("/all-available-rooms/{start}/{end}")
    public List<Room> getAllAvailableRoomsInTimeFrame(@PathVariable("start") String start,
                                                      @PathVariable("end") String end) {
        return reservationRepository.getAllAvailableRoomsInTimeFrame(LocalDate.parse(start), LocalDate.parse(end));
    }

    @GetMapping("/available-number-of-available-categories/{start}/{end}")
    public int getNumberOfAllAvailableRoomsInTimeFrame(@PathVariable("start") String start,
                                                       @PathVariable("end") String end) {
        return reservationRepository.getNumberOfAvailableRoomsByCategoryInTimeFrame(LocalDate.parse(start), LocalDate.parse(end));
    }

    @GetMapping("/numberOfAvailableRoomsByCategory/{start}/{end}")
    public Map<Category, Integer> getNumberOfAvailableRoomsByCategoryInTimeFrame(@PathVariable("start") String start,
                                                                                 @PathVariable("end") String end) {
        return reservationRepository.getAllAvailableRoomsByCategoryInTimeFrame(LocalDate.parse(start), LocalDate.parse(end));
    }

//    @RequestMapping(value = "/numberOfAvailableRoomsByCategory/{id}/{start}/{end}", method = RequestMethod.GET)
//    public int getNumberOfAvailableRoomsByCategoryId(@PathVariable("id") Long id,
//                                                     @PathVariable("start") String start,
//                                                     @PathVariable("end") String end) {
//        return reservationRepository.getNumberOfAvailableRoomsByCategoryId(id, LocalDate.parse(start), LocalDate.parse(end));
//    }

//    // TODO: write JPQL check query instead of backend logic
//    @RequestMapping(value = "/category/available/{id}/{start}/{end}", method = RequestMethod.POST)
//    public boolean checkIfCategoryAvailableInTimeFrameById(@PathVariable("id") Long id,
//                                                           @PathVariable("start") String start,
//                                                           @PathVariable("end") String end) {
//        // TODO: check if original logic produces different results
////        List<Reservation> allRoomsOfType = reservationRepository.getAllById(id);
////        List<Reservation> takenRooms = reservationRepository.getAvailableReservations(LocalDate.parse(start), LocalDate.parse(end));
////        return takenRooms.size() < allRoomsOfType.size();
//        int availableRoomNumber = getNumberOfAvailableRoomsByCategoryId(id, start, end);
//        return availableRoomNumber > 0;
//    }

    @GetMapping("/allOccupiedRooms/{start}/{end}")
    public List<Room> getAllOccupiedRooms(@PathVariable("start") String start,
                                          @PathVariable("end") String end) {
        return reservationRepository.getAllOccupiedRoomsInTimeFrame(LocalDate.parse(start), LocalDate.parse(end));
    }
}
