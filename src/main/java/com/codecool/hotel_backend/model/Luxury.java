package com.codecool.hotel_backend.model;


public class Luxury extends Category{
    public Luxury(){
        id=1;  
        name = "Luxury Room";
        description = "These rooms provide you not only great views of the city but space to relax with your friends and family, while enjoying a special welcome amenity.";
        imgUrl = "https://pix10.agoda.net/hotelImages/160/1601291/1601291_17062916290054159438.jpg?s=1024x768";
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
