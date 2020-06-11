package com.codecool.hotel_backend.repository;

import ch.qos.logback.core.boolex.EvaluationException;
import com.codecool.hotel_backend.entity.Category;
import com.codecool.hotel_backend.entity.Reservation;
import com.codecool.hotel_backend.entity.ReservedRoom;
import com.codecool.hotel_backend.entity.Room;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query(value = "select * from Reservation left join " +
            "Reserved_Room on Reserved_Room.reservation_id = Reservation.id"
    , nativeQuery=true)
    List<Reservation> getAllReservationJoin();

    List<Reservation> getAllById(Long id);

    @Query(value = "SELECT res.id, res.startDate, res.endDate, res.category.id\n" +
            " FROM Reservation res\n" +
            " WHERE :startDate NOT BETWEEN res.startDate AND res.endDate\n" +
            " AND :endDate NOT BETWEEN res.startDate AND res.endDate\n" //+
//            " AND res.category.id NOT IN " +
//            "(SELECT DISTINCT b.car.id FROM BorrowedDate b WHERE :userDateStart BETWEEN b.startDate AND b.endDate OR :userDateEnd BETWEEN b.startDate AND b.endDate)\n"
    )
    List<Reservation> getAvailableReservations(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Reservation> findAll();

    //@Query("SELECT reservation FROM Reservation reservation WHERE reservation.id = :res_id")
    Reservation findReservationById(Long Id);

    @Modifying
    @Query("UPDATE Reservation res SET res.startDate = :newStartDate, res.endDate = :newEndDate, res.reservedRoom = :reserved_room " +
            "WHERE res.id = :reservation_id")
    void updateReservation(@Param("reservation_id") Long reservationId,
                           @Param("newStartDate") LocalDate startDate,
                           @Param("newEndDate") LocalDate endDate,
                           @Param("reserved_room")ReservedRoom reservedRoom);

    @Query(value = "SELECT room FROM Room room" +
            " WHERE room.id NOT IN" +
            " (SELECT rr FROM ReservedRoom rr WHERE rr.id NOT IN" +
            " (SELECT res FROM Reservation res WHERE" +
            " (:startDate BETWEEN res.startDate AND res.endDate" +
            " AND :endDate BETWEEN res.startDate AND res.endDate) OR" +
            " :startDate < res.startDate AND :endDate > res.endDate) )")
    List<Room> getAllAvailableRoomsInTimeFrame(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT room FROM Room room" +
            " WHERE room.id IN" +
            " (SELECT rr FROM ReservedRoom rr WHERE rr.id IN" +
            " (SELECT res FROM Reservation res WHERE" +
            " (:startDate BETWEEN res.startDate AND res.endDate" +
            " AND :endDate BETWEEN res.startDate AND res.endDate) OR" +
            " :startDate < res.startDate AND :endDate > res.endDate) )")
    List<Room> getAllOccupiedRoomsInTimeFrame(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT COUNT(room) FROM Room room" +
            " WHERE room.id NOT IN" +
            " (SELECT rr.room.id FROM ReservedRoom rr WHERE rr.id NOT IN" +
            " (SELECT res.reservedRoom.id FROM Reservation res WHERE" +
            " (:startDate BETWEEN res.startDate AND res.endDate" +
            " AND :endDate BETWEEN res.startDate AND res.endDate) OR" +
            " :startDate <= res.startDate AND :endDate >= res.endDate) )")
    int getNumberOfAvailableRoomsInTimeFrame(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT COUNT(room) FROM Room room" +
            " WHERE room.id NOT IN" +
            " (SELECT rr FROM ReservedRoom rr WHERE rr.id NOT IN" +
            " (SELECT res FROM Reservation res WHERE" +
            " (:startDate BETWEEN res.startDate AND res.endDate" +
            " AND :endDate BETWEEN res.startDate AND res.endDate) OR" +
            " :startDate < res.startDate AND :endDate > res.endDate) )" +
            " GROUP BY room.category")
    Map<Category, Integer> getAllAvailableRoomsByCategoryInTimeFrame(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


    // TODO: fix issue with optional ID error in room.category.id
//    @Query(value = "SELECT COUNT(room) FROM Room room" +
//            " WHERE room.id NOT IN" +
//            " (SELECT rr FROM ReservedRoom rr WHERE rr.id NOT IN" +
//            " (SELECT res FROM Reservation res WHERE" +
//            " (:startDate BETWEEN res.startDate AND res.endDate" +
//            " AND :endDate BETWEEN res.startDate AND res.endDate) OR" +
//            " :startDate < res.startDate AND :endDate > res.endDate) )" +
//            " AND room.category.id = :categoryId" +
//            " GROUP BY room.category")
//    int getNumberOfAvailableRoomsByCategoryId(@Param("id") Long categoryId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
