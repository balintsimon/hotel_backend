package com.codecool.hotel_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ReservedRoom {
    @Id
    @GeneratedValue
    private Long Id;

    @OneToOne
    private Reservation reservation;

    @ManyToOne
    private Room room;
}
