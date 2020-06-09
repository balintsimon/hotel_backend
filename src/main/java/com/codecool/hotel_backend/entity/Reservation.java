package com.codecool.hotel_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Category category;

    private LocalDate startDate;
    private LocalDate endDate;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private ReservedRoom reservedRoom;

}
