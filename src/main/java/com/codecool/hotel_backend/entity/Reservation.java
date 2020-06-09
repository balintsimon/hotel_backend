package com.codecool.hotel_backend.entity;

import lombok.*;

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
    private Long Id;

    @ManyToOne
    private Category category;

    private LocalDate startDate;
    private LocalDate endDate;


    @OneToOne(mappedBy = "reservation", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    private ReservedRoom reservedRoom;

}
