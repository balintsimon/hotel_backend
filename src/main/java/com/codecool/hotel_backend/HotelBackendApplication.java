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
                    .name("Luxury Room")
                    .capacity(6L)
                    .description("These rooms provide you not only great views of the city but space to relax with your friends and family, while enjoying a special welcome amenity.")
                    .imgUrl("https://pix10.agoda.net/hotelImages/160/1601291/1601291_17062916290054159438.jpg?s=1024x768")
                    .size(25L)
                    .build();

            Category superiorStreetView = Category.builder()
                    .Id(2L)
                    .name("Superior Street View Room")
                    .capacity(2L)
                    .description("Take in all what Budapest has to offer while staying close to all the key acts in the city.")
                    .imgUrl("https://q-xx.bstatic.com/xdata/images/hotel/840x460/183479087.jpg?k=cf9f2554a81a9bc9e5e661af0fe034e09ec867dfa90112a6668afe590eddec31&o=")
                    .size(15L)
                    .build();

            Category rockstarSuite = Category.builder()
                    .Id(3L)
                    .name("Rockstar Suite Room")
                    .capacity(4L)
                    .description("Our trademark Rock Star Suite is the perfect choice for the after party - minibar included. Have your crew over to chill or enjoy some great bites on your own terrace, then let good times flow in your own jacuzzi.")
                    .imgUrl("https://www.dertourluxury.hu/upload/pics/hotel/gallery/C__WebServ_Webs_Fundacion_folletos_download_742414550_HardRock%20HardRock%20RockstarSuite_Foyerk.jpg")
                    .size(20L)
                    .build();



            categoryRepository.save(luxury);
            categoryRepository.save(superiorStreetView);
            categoryRepository.save(rockstarSuite);
        };

    }

}
