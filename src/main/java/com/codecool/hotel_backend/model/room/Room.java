package com.codecool.hotel_backend.model.room;

import lombok.Getter;
import lombok.Setter;

public abstract class Room {
    private static int idCounter = 0;
    protected @Getter @Setter int id;
    protected @Getter @Setter int capacity;
    protected @Getter @Setter String description;
    protected @Getter @Setter String category;
    protected @Getter @Setter int size;
    protected @Getter @Setter boolean isOccupied;

    public Room() {
        id = idCounter++;
        this.isOccupied = false;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", size=" + size +
                ", isOccupied=" + isOccupied +
                '}';
    }
}
