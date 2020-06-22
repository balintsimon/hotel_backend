package com.codecool.hotel_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
//    @JsonIgnore
    @JsonManagedReference
    @OneToOne(mappedBy = "reservation",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private ReservedRoom reservedRoom;

}
