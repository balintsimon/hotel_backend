package com.codecool.hotel_backend.model;


public class Luxury extends Category{
    public Luxury(){
        id=1;  
        name = "Luxury Room";
        description = "Description for luxury room";
        capacity = 6;
        size = 25;
    }

    @Override
    public String toString() {
        return "Luxury{" +
                "id=" + id +
                ", size=" + size +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
