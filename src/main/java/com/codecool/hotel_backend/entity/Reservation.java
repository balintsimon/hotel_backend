package com.codecool.hotel_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;

    @ManyToOne
    private Category category;

    private LocalDate startDate;
    private LocalDate endDate;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToOne(mappedBy = "reservation", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private ReservedRoom reservedRoom;

}
