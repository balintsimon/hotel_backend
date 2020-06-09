package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.Category;
import com.codecool.hotel_backend.entity.Reservation;
import com.codecool.hotel_backend.entity.ReservedRoom;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

//    private Long id;
//    private Category category;
//    private LocalDate startDate;
//    private LocalDate endDate;
//    private ReservedRoom reservedRoom;



}
