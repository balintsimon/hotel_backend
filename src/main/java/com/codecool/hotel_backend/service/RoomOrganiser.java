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
public class RoomOrganiser {

    private OrganiserUtils organiserUtils;
    private CategoryRepository categoryRepository;
    private ReservationRepository reservationRepository;
    private ReservedRoomRepository reservedRoomRepository;
    private RoomRepository roomRepository;

    @Autowired
    public RoomOrganiser(OrganiserUtils organiserUtils, CategoryRepository categoryRepository, ReservationRepository reservationRepository, ReservedRoomRepository reservedRoomRepository, RoomRepository roomRepository) {
        this.organiserUtils = organiserUtils;
        this.categoryRepository = categoryRepository;
        this.reservationRepository = reservationRepository;
        this.reservedRoomRepository = reservedRoomRepository;
        this.roomRepository = roomRepository;
    }

    public List<Room> getAvailableRooms(String start, String end) {
        LocalDate startDate = organiserUtils.convertStringToLocalDate(start);
        LocalDate endDate = organiserUtils.convertStringToLocalDate(end);
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> foundReservations = organiserUtils.findReservationsInTimeFrame(startDate, endDate, reservations);

        List<ReservedRoom> allReservedRooms = reservedRoomRepository.findAll();
        List<ReservedRoom> allFoundReservedRooms = organiserUtils.getReservedRoomsFromReservations(foundReservations, allReservedRooms);
        List<Room> allFoundRooms = organiserUtils.getRoomsFromReservedRooms(allFoundReservedRooms);

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

    public boolean reserveRoom(Long categoryId, String start, String end) {
        List<Room> availableRooms = getAvailableRoomsInCategory(start, end, categoryId);
        LocalDate startDate = organiserUtils.convertStringToLocalDate(start);
        LocalDate endDate = organiserUtils.convertStringToLocalDate(end);

        if (availableRooms != null) {
            Reservation reservation = Reservation.builder()
                    .category(categoryRepository.findCategoryById(categoryId))
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();
            reservationRepository.saveAndFlush(reservation);
            return true;
        }
        return false;
    }

    public Room getFirstAvailableRoomInCategory(String start, String end, Long categoryId) {
        List<Room> allAvailableRooms = getAvailableRoomsInCategory(start, end, categoryId);
        if (allAvailableRooms != null) {
            return allAvailableRooms.get(0);
        }
        return null;
    }

    public List<Room> getAvailableRoomsInCategory(String start, String end, Long categoryId) {
        LocalDate startDate = organiserUtils.convertStringToLocalDate(start);
        LocalDate endDate = organiserUtils.convertStringToLocalDate(end);
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> foundReservations = organiserUtils.findReservationsInTimeFrame(startDate, endDate, reservations);

        List<ReservedRoom> allReservedRooms = reservedRoomRepository.findAll();
        List<ReservedRoom> allFoundReservedRooms = organiserUtils.getReservedRoomsFromReservations(foundReservations, allReservedRooms);
        List<Room> allFoundRooms = organiserUtils.getRoomsFromReservedRooms(allFoundReservedRooms);

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
        LocalDate startDate = organiserUtils.convertStringToLocalDate(start);
        LocalDate endDate = organiserUtils.convertStringToLocalDate(end);
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> foundReservations = organiserUtils.findReservationsInTimeFrame(startDate, endDate, reservations);

        List<ReservedRoom> allReservedRooms = reservedRoomRepository.findAll();
        List<ReservedRoom> allFoundReservedRooms = organiserUtils.getReservedRoomsFromReservations(foundReservations, allReservedRooms);
        List<Room> allFoundRooms = organiserUtils.getRoomsFromReservedRooms(allFoundReservedRooms);

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
        LocalDate startDate = organiserUtils.convertStringToLocalDate(start);
        LocalDate endDate = organiserUtils.convertStringToLocalDate(end);
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> foundReservations = organiserUtils.findReservationsInTimeFrame(startDate, endDate, reservations);

        List<ReservedRoom> allReservedRooms = reservedRoomRepository.findAll();
        List<ReservedRoom> allFoundReservedRooms = organiserUtils.getReservedRoomsFromReservations(foundReservations, allReservedRooms);
        List<Room> allFoundRooms = organiserUtils.getRoomsFromReservedRooms(allFoundReservedRooms);

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


    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
