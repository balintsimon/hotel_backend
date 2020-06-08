package com.codecool.hotel_backend.entity;

import com.codecool.hotel_backend.entity.Category;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long Id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private boolean isOccupied;


}
