package com.codecool.hotel_backend.service;

import com.codecool.hotel_backend.entity.Reservation;
import com.codecool.hotel_backend.entity.ReservedRoom;
import com.codecool.hotel_backend.entity.Room;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrganiserUtils {
    List<Room> getRoomsFromReservedRooms(List<ReservedRoom> reservedRooms) {
        List<Room> rooms = new ArrayList<>();
        for (ReservedRoom actualReservedRoom : reservedRooms) {
            rooms.add(actualReservedRoom.getRoom());
        }
        return rooms;
    }

    List<ReservedRoom> getReservedRoomsFromReservations(List<Reservation> reservations, List<ReservedRoom> reservedRooms) {
        return reservedRooms.parallelStream()
                .filter(reservedRoom -> reservations.contains(reservedRoom.getReservation()))
                .collect(Collectors.toList());

        // Learn map filter reduce


//        List<ReservedRoom> foundReservedRooms = new ArrayList<>();
//
//        for (ReservedRoom actualReservedRoom : reservedRooms) {
//            if (reservations.contains(actualReservedRoom.getReservation())) {
//                foundReservedRooms.add(actualReservedRoom);
//            }
//        }
//        return foundReservedRooms;
    }

    List<Reservation> findReservationsInTimeFrame(LocalDate startDate, LocalDate endDate, List<Reservation> reservations) {

        // refactor to functional
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
        return foundReservations;
    }

    LocalDate convertStringToLocalDate(String dateInString) {
        LocalDate convertedDate = null;
        try {
            convertedDate = LocalDate.parse(dateInString);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }
}
