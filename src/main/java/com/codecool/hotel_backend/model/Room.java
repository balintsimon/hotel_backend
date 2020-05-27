package com.codecool.hotel_backend.model;

import lombok.Getter;
import lombok.Setter;

public class Room {
    private static int idCounter = 0;
    private  @Getter @Setter int id;
    private  @Getter @Setter Category category;
    private  @Getter @Setter boolean isOccupied;

    public Room(Category category) {
        this.category = category;
        id = idCounter++;
        this.isOccupied = false;
    }
}
