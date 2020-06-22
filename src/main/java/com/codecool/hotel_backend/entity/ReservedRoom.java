package com.codecool.hotel_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@JsonIgnoreProperties({"reservation"})
public class ReservedRoom {
    @Id
    @GeneratedValue
    private Long id;

    @JsonBackReference
    @OneToOne
    private Reservation reservation;

    @ManyToOne
    private Room room;
}
