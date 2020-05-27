package com.codecool.hotel_backend.model;

import lombok.Getter;
import lombok.Setter;

public abstract class Category {
    //private static int idCounter = 0;
    protected @Getter @Setter int id;
    protected @Getter @Setter int size;
    protected @Getter @Setter String description;
    protected @Getter @Setter int capacity;

    /*public Category(){
        this.id = idCounter++;
    }*/
}
