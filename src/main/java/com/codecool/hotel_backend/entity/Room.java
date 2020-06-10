package com.codecool.hotel_backend.entity;

import com.codecool.hotel_backend.entity.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Category category;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ReservedRoom> reservedRoomList;

}
