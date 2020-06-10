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
        LocalDate startDate = convertStringToLocalDate(start);
        LocalDate endDate = convertStringToLocalDate(end);
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> foundReservations = findReservationsInTimeFrame(startDate, endDate, reservations);

        List<ReservedRoom> allReservedRooms = reservedRoomRepository.findAll();
        List<ReservedRoom> allFoundReservedRooms = getReservedRoomsFromReservations(foundReservations, allReservedRooms);
        List<Room> allFoundRooms = getRoomsFromReservedRooms(allFoundReservedRooms);

        List<Room> allRooms = roomRepository.findAll();
        List<Room> availableRooms = new ArrayList<>();
        List<Room> takenRooms = new ArrayList<>();

        for (Room actualRoom : allRooms) {
            if (!allFoundRooms.contains(actualRoom)) {
                availableRooms.add(actualRoom);
            } else {
                takenRooms.add(actualRoom);
            }
        }

        System.out.println(takenRooms);
        return availableRooms;
    }

    public List<Room> getAvailableRoomsInCategory(String start, String end, Long categoryId) {
        LocalDate startDate = convertStringToLocalDate(start);
        LocalDate endDate = convertStringToLocalDate(end);
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> foundReservations = findReservationsInTimeFrame(startDate, endDate, reservations);

        List<ReservedRoom> allReservedRooms = reservedRoomRepository.findAll();
        List<ReservedRoom> allFoundReservedRooms = getReservedRoomsFromReservations(foundReservations, allReservedRooms);
        List<Room> allFoundRooms = getRoomsFromReservedRooms(allFoundReservedRooms);

        List<Room> allRooms = roomRepository.findAllByCategory_Id(categoryId);
        List<Room> availableRooms = new ArrayList<>();
        List<Room> takenRooms = new ArrayList<>();

        for (Room actualRoom : allRooms) {
            if (!allFoundRooms.contains(actualRoom)) {
                availableRooms.add(actualRoom);
            } else {
                takenRooms.add(actualRoom);
            }
        }

        System.out.println(availableRooms.size() + " number of available");
        System.out.println(takenRooms.size() + " number of taken");

        return availableRooms;
    }

    public List<Room> getTakenRooms(String start, String end) {
        LocalDate startDate = convertStringToLocalDate(start);
        LocalDate endDate = convertStringToLocalDate(end);
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> foundReservations = findReservationsInTimeFrame(startDate, endDate, reservations);

        List<ReservedRoom> allReservedRooms = reservedRoomRepository.findAll();
        List<ReservedRoom> allFoundReservedRooms = getReservedRoomsFromReservations(foundReservations, allReservedRooms);
        List<Room> allFoundRooms = getRoomsFromReservedRooms(allFoundReservedRooms);

        List<Room> allRooms = roomRepository.findAll();
        List<Room> availableRooms = new ArrayList<>();
        List<Room> takenRooms = new ArrayList<>();

        for (Room actualRoom : allRooms) {
            if (!allFoundRooms.contains(actualRoom)) {
                availableRooms.add(actualRoom);
            } else {
                takenRooms.add(actualRoom);
            }
        }

        System.out.println(takenRooms);
        return takenRooms;
    }

    public List<Room> getTakenRoomsInCategory(String start, String end, Long categoryId) {
        LocalDate startDate = convertStringToLocalDate(start);
        LocalDate endDate = convertStringToLocalDate(end);
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> foundReservations = findReservationsInTimeFrame(startDate, endDate, reservations);

        List<ReservedRoom> allReservedRooms = reservedRoomRepository.findAll();
        List<ReservedRoom> allFoundReservedRooms = getReservedRoomsFromReservations(foundReservations, allReservedRooms);
        List<Room> allFoundRooms = getRoomsFromReservedRooms(allFoundReservedRooms);

        List<Room> allRooms = roomRepository.findAllByCategory_Id(categoryId);
        List<Room> availableRooms = new ArrayList<>();
        List<Room> takenRooms = new ArrayList<>();

        for (Room actualRoom : allRooms) {
            if (!allFoundRooms.contains(actualRoom)) {
                availableRooms.add(actualRoom);
            } else {
                takenRooms.add(actualRoom);
            }
        }

        System.out.println(availableRooms.size() + " number of available");
        System.out.println(takenRooms.size() + " number of taken");

        return takenRooms;
    }

    private List<Room> getRoomsFromReservedRooms(List<ReservedRoom> reservedRooms) {
        List<Room> rooms = new ArrayList<>();
        for (ReservedRoom actualReservedRoom : reservedRooms) {
            rooms.add(actualReservedRoom.getRoom());
        }
        return rooms;
    }

    private List<ReservedRoom> getReservedRoomsFromReservations(List<Reservation> reservations, List<ReservedRoom> reservedRooms) {
        List<ReservedRoom> foundReservedRooms = new ArrayList<>();

        for (ReservedRoom actualReservedRoom : reservedRooms) {
            if (reservations.contains(actualReservedRoom.getReservation())) {
                foundReservedRooms.add(actualReservedRoom);
            }
        }
        return foundReservedRooms;
    }

    private List<Reservation> findReservationsInTimeFrame(LocalDate startDate, LocalDate endDate, List<Reservation> reservations) {
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

    private LocalDate convertStringToLocalDate(String dateInString) {
        LocalDate convertedDate = null;
        try {
            convertedDate = LocalDate.parse(dateInString);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

}
