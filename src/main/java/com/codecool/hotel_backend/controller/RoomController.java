package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.entity.Category;
import com.codecool.hotel_backend.entity.Room;
import com.codecool.hotel_backend.repository.ReservationRepository;
import com.codecool.hotel_backend.repository.ReservedRoomRepository;
import com.codecool.hotel_backend.repository.RoomRepository;
import com.codecool.hotel_backend.service.RoomOrganiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins = {"http://localhost:8080"})
@RequestMapping("/room")
public class RoomController {
    private RoomRepository roomRepository;
    private ReservationRepository reservationRepository;
    private ReservedRoomRepository reservedRoomRepository;
    private RoomOrganiser roomOrganiser;

    @Autowired
    public RoomController(RoomRepository roomRepository, ReservedRoomRepository reservedRoomRepository, ReservationRepository reservationRepository, RoomOrganiser roomOrganiser) {
        this.roomRepository = roomRepository;
        this.reservedRoomRepository = reservedRoomRepository;
        this.reservationRepository = reservationRepository;
        this.roomOrganiser = roomOrganiser;
    }

    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/all-available/{start}/{end}")
    public List<Room> getAllAvailableRoomsInTimeFrame(@PathVariable("start") String start,
                                                      @PathVariable("end") String end) {
        return roomOrganiser.getAvailableRooms(start, end);
    }

    @GetMapping("/all-taken/{start}/{end}")
    public List<Room> getAllTakenRoomsInTimeFrame(@PathVariable("start") String start,
                                                      @PathVariable("end") String end) {
        return roomOrganiser.getTakenRooms(start, end);
    }

    @GetMapping("/all-available-category/{start}/{end}/{id}")
    public List<Room> getAllAvailableRoomsInTimeFrameInCategory(@PathVariable("id") Long id,
                                                                @PathVariable("start") String start,
                                                      @PathVariable("end") String end) {
        return roomOrganiser.getAvailableRoomsInCategory(start, end, id);
    }

    @GetMapping("/all-taken-category/{start}/{end}/{id}")
    public List<Room> getAllTakenRoomsInTimeFrameInCategory(@PathVariable("id") Long id,
                                                            @PathVariable("start") String start,
                                                      @PathVariable("end") String end) {
        return roomOrganiser.getTakenRoomsInCategory(start, end, id);
    }



    @GetMapping("/available-number-of-available-rooms/{start}/{end}")
    public int getNumberOfAllAvailableRoomsInTimeFrame(@PathVariable("start") String start,
                                                       @PathVariable("end") String end) {
        return reservationRepository.getNumberOfAvailableRoomsInTimeFrame(LocalDate.parse(start), LocalDate.parse(end));
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
