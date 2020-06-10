package com.codecool.hotel_backend.service;

import com.codecool.hotel_backend.entity.Reservation;
import com.codecool.hotel_backend.entity.ReservedRoom;
import com.codecool.hotel_backend.entity.Room;
import com.codecool.hotel_backend.repository.CategoryRepository;
import com.codecool.hotel_backend.repository.ReservationRepository;
import com.codecool.hotel_backend.repository.ReservedRoomRepository;
import com.codecool.hotel_backend.repository.RoomRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoomOrganizer {

    private CategoryRepository categoryRepository;
    private ReservationRepository reservationRepository;
    private ReservedRoomRepository reservedRoomRepository;
    private RoomRepository roomRepository;

    @Autowired
    public RoomOrganizer(CategoryRepository categoryRepository, ReservationRepository reservationRepository, ReservedRoomRepository reservedRoomRepository, RoomRepository roomRepository) {
        this.categoryRepository = categoryRepository;
        this.reservationRepository = reservationRepository;
        this.reservedRoomRepository = reservedRoomRepository;
        this.roomRepository = roomRepository;
    }

    public List<Room> getAvailableRooms(String start, String end) {
        LocalDate startDate = null;
        LocalDate endDate = null;
        try {
            startDate = LocalDate.parse(start);
            endDate = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        // 1. Get reservations
        List<Reservation> reservations = reservationRepository.findAll();

        List<Reservation> foundReservations = new ArrayList<>();
         for (Reservation reservation : reservations) {
            assert startDate != null;
            assert endDate != null;
            if ((startDate.isBefore(reservation.getEndDate()) && startDate.isAfter(reservation.getStartDate()))
                    || (startDate.isEqual(reservation.getStartDate()) || startDate.isEqual(reservation.getEndDate()))
                || (endDate.isBefore(reservation.getEndDate()) && endDate.isAfter(reservation.getStartDate()))
                    || (endDate.isEqual(reservation.getStartDate()) || endDate.isEqual(reservation.getEndDate()))
                || (startDate.isBefore(reservation.getStartDate()) && endDate.isAfter(reservation.getEndDate()))
            ) {
                foundReservations.add(reservation);
            }
        }
        System.out.println("==================================");
        System.out.println("Found the following reservations in the time frame:");
        for (Reservation foundreservation : foundReservations) {
            System.out.println(foundreservation.toString());
        }

        // 2. Get reserved rooms
        List<ReservedRoom> allReservedRooms = reservedRoomRepository.findAll();
        List<ReservedRoom> allFoundReservedRooms = new ArrayList<>();
        List<Room> allFoundRooms = new ArrayList<>();
        for (ReservedRoom actualReservedRoom : allReservedRooms) {
            if (foundReservations.contains(actualReservedRoom.getReservation())) {
                allFoundReservedRooms.add(actualReservedRoom);
                allFoundRooms.add(actualReservedRoom.getRoom());
            }
        }

        System.out.println("Found the following reserved rooms in the time frame:");
        for (ReservedRoom foundreservation : allFoundReservedRooms) {
            System.out.println(foundreservation.toString());
        }

        // 3. Get all rooms
        List<Room> allRooms = roomRepository.findAll();
        List<Room> availableRooms = new ArrayList<>();
        List<Room> takenRooms = new ArrayList<>();

        for (Room actualRoom : allRooms) {
            if (allFoundRooms.contains(actualRoom)) {
                takenRooms.add(actualRoom);
            } else {
                availableRooms.add(actualRoom);
            }
        }

        System.out.println("Found the following rooms are taken in the time frame:");
        for (Room room : takenRooms) {
            System.out.println(room.toString());
        }
        System.out.println(availableRooms.size() + " number of rooms are available");
        System.out.println(takenRooms.size() + " number of rooms are taken");
        System.out.println("==================================");
        return availableRooms;
    }

}
