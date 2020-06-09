package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.Category;
import com.codecool.hotel_backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

//    private Long id;
//    private Category category;
//    private LocalDate startDate;
//    private LocalDate endDate;
//    private ReservedRoom reservedRoom;

    List<Reservation> getAllById(Long id);

    @Query(value = "SELECT res.id, res.startDate, res.endDate, res.category.id\n" +
            " FROM Reservation res\n" +
            " WHERE :startDate NOT BETWEEN res.startDate AND res.endDate\n" +
            " AND :endDate NOT BETWEEN res.startDate AND res.endDate\n" //+
//            " AND res.category.id NOT IN " +
//            "(SELECT DISTINCT b.car.id FROM BorrowedDate b WHERE :userDateStart BETWEEN b.startDate AND b.endDate OR :userDateEnd BETWEEN b.startDate AND b.endDate)\n"
            )
    List<Reservation> getAvailableReservations(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
