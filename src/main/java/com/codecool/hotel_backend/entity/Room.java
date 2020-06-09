package com.codecool.hotel_backend.entity;

import com.codecool.hotel_backend.entity.Category;
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

    @OneToMany(mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ReservedRoom> reservedRoomList;

}
