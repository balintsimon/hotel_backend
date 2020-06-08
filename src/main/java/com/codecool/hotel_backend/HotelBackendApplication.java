package com.codecool.hotel_backend;

import com.codecool.hotel_backend.entity.Category;
import com.codecool.hotel_backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class HotelBackendApplication {

    @Autowired
    CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(HotelBackendApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            Category luxury = Category.builder()
                    .Id(1L)
                    .capacity(6L)
                    .description("These rooms provide you not only great views of the city but space to relax with your friends and family, while enjoying a special welcome amenity.")
                    .imgUrl("https://pix10.agoda.net/hotelImages/160/1601291/1601291_17062916290054159438.jpg?s=1024x768")
                    .size(25L)
                    .build();

            categoryRepository.save(luxury);
        };

    }

}
