package com.codecool.hotel_backend.model;

import lombok.Getter;
import lombok.Setter;

public abstract class Category {
    protected @Getter @Setter int id;
    protected @Getter @Setter int size;
    protected @Getter @Setter String name;
    protected @Getter @Setter String description;
    protected @Getter @Setter int capacity;
    protected @Getter @Setter String imgUrl;
}
