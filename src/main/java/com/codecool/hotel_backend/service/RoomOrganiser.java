package com.codecool.hotel_backend.service;

import com.codecool.hotel_backend.controller.ControllerUtil;
import com.codecool.hotel_backend.entity.*;
import com.codecool.hotel_backend.repository.CategoryRepository;
import com.codecool.hotel_backend.repository.ReservationRepository;
import com.codecool.hotel_backend.repository.ReservedRoomRepository;
import com.codecool.hotel_backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoomOrganiser {

    private final OrganiserUtils organiserUtils;
    private final CategoryRepository categoryRepository;
    private final ReservationRepository reservationRepository;
    private final ReservedRoomRepository reservedRoomRepository;
    private final RoomRepository roomRepository;
    private final ControllerUtil controllerUtil;

    @Autowired
    public RoomOrganiser(OrganiserUtils organiserUtils, CategoryRepository categoryRepository, ReservationRepository reservationRepository, ReservedRoomRepository reservedRoomRepository, RoomRepository roomRepository, ControllerUtil controllerUtil) {
        this.organiserUtils = organiserUtils;
        this.categoryRepository = categoryRepository;
        this.reservationRepository = reservationRepository;
        this.reservedRoomRepository = reservedRoomRepository;
        this.roomRepository = roomRepository;
        this.controllerUtil = controllerUtil;
    }

    public boolean finaliseReservation(Long reservationId, Long roomId, String start, String end) throws IllegalArgumentException {

        LocalDate startDate = organiserUtils.convertStringToLocalDate(start);
        LocalDate endDate = organiserUtils.convertStringToLocalDate(end);

        Reservation foundReservation = reservationRepository.findReservationById(reservationId);
        Room foundRoom = roomRepository.findRoomById(roomId);

        if (foundRoom == null) throw new IllegalArgumentException();
        if (foundReservation == null) throw new IllegalArgumentException();

        ReservedRoom reservedRoom = reservedRoomRepository.findByReservationId(foundReservation.getId());
        boolean roomAlreadyReserved = true;
        if (reservedRoom == null) {
            reservedRoom = ReservedRoom.builder().reservation(foundReservation).room(foundRoom).build();
            roomAlreadyReserved = false;
        }

        foundReservation.setStartDate(startDate);
        foundReservation.setEndDate(endDate);

        reservationRepository.updateReservation(reservationId, startDate, endDate); // This properly updates the reservation
        if (roomAlreadyReserved) {
            reservedRoomRepository.updateReservedRoom(reservedRoom.getId(), roomId); // This updates the reserved room if needed
        } else {        System.out.println("was here");
            reservedRoomRepository.save(reservedRoom);
        }

        return true;
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

    public boolean reserveRoomCategory(Long categoryId, String start, String end, HotelUser hotelUser) {
        // Check if category returns anything, if not break

        Category currentCategory = categoryRepository.findCategoryById(categoryId);

        if (currentCategory == null) return false;

        List<Room> availableRooms = getAvailableRoomsInCategory(start, end, categoryId);
        LocalDate startDate = organiserUtils.convertStringToLocalDate(start);
        LocalDate endDate = organiserUtils.convertStringToLocalDate(end);
        LocalDate currentDate = LocalDate.now();
        if (endDate.isBefore(startDate)) {
            return false;
        } else if (startDate.isBefore(currentDate)){
            return false;
        }

        if (availableRooms != null) {
            Reservation reservation = Reservation.builder()
                    .category(categoryRepository.findCategoryById(categoryId))
                    .startDate(startDate)
                    .endDate(endDate)
                    .user(hotelUser)
                    .build();
            reservationRepository.saveAndFlush(reservation);
            return true;
        }
        return false;
    }

    public List<Category> getAvailableCategoriesInTimeFrame(String start, String end) {
        List<Category> availableCategories = new ArrayList<>(); // return list

        List<Category> categoryList = categoryRepository.findAll();

        for (Category category : categoryList) {
            List<Reservation> takenRooms = getReservedRoomInCategoryInTimeFrame(start, end, category.getId());
            List<Room> allRoomsInCategory = roomRepository.findAllByCategory_Id(category.getId());
            if (allRoomsInCategory.size() > takenRooms.size()) {
                availableCategories.add(category);
            }
        }
        return availableCategories;
    }

    public List<Reservation> getReservedRoomInCategoryInTimeFrame(String start, String end, Long categoryId) {
        List<Reservation> reservationsInCategoryInTimeFrame = new ArrayList<>();

        LocalDate startDate = organiserUtils.convertStringToLocalDate(start);
        LocalDate endDate = organiserUtils.convertStringToLocalDate(end);
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> foundReservations = organiserUtils.findReservationsInTimeFrame(startDate, endDate, reservations);

        for (Reservation actualReservation : foundReservations) {
            System.out.println(actualReservation);
            if (actualReservation.getCategory().getId() == categoryId) {
                reservationsInCategoryInTimeFrame.add(actualReservation);
            }
        }
        return reservationsInCategoryInTimeFrame;
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
    
    public List<Reservation> getMyReservations(String authorization) {
        List<Reservation> myReservations = new ArrayList<>();
        List<Reservation> allReservations = getAllReservations();
        try {
            HotelUser myUser = controllerUtil.getUserFromToken(authorization);
            for (Reservation actualReservation : allReservations) {
                if (actualReservation.getUser() == myUser) {
                    myReservations.add(actualReservation);
                }
            }
            return myReservations;
        } catch (Error e) {
            System.out.println(e);
            return null;
        }
    }
}
