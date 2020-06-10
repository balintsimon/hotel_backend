package com.codecool.hotel_backend;

import com.codecool.hotel_backend.entity.Category;
import com.codecool.hotel_backend.entity.Reservation;
import com.codecool.hotel_backend.entity.ReservedRoom;
import com.codecool.hotel_backend.entity.Room;
import com.codecool.hotel_backend.repository.CategoryRepository;
import com.codecool.hotel_backend.repository.ReservationRepository;
import com.codecool.hotel_backend.repository.ReservedRoomRepository;
import com.codecool.hotel_backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HotelBackendApplication {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservedRoomRepository reservedRoomRepository;

    public static void main(String[] args) {
        SpringApplication.run(HotelBackendApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            Category luxury = Category.builder()
                    .name("Luxury Room")
                    .capacity(6L)
                    .description("These rooms provide you not only great views of the city but space to relax with your friends and family, while enjoying a special welcome amenity.")
                    .imgUrl("https://pix10.agoda.net/hotelImages/160/1601291/1601291_17062916290054159438.jpg?s=1024x768")
                    .size(25L)
                    .build();

            Category superiorStreetView = Category.builder()
                    .name("Superior Street View Room")
                    .capacity(2L)
                    .description("Take in all what Budapest has to offer while staying close to all the key acts in the city.")
                    .imgUrl("https://q-xx.bstatic.com/xdata/images/hotel/840x460/183479087.jpg?k=cf9f2554a81a9bc9e5e661af0fe034e09ec867dfa90112a6668afe590eddec31&o=")
                    .size(15L)
                    .build();

            Category rockStarSuite = Category.builder()
                    .name("Rockstar Suite Room")
                    .capacity(4L)
                    .description("Our trademark Rock Star Suite is the perfect choice for the after party - minibar included. Have your crew over to chill or enjoy some great bites on your own terrace, then let good times flow in your own jacuzzi.")
                    .imgUrl("https://www.dertourluxury.hu/upload/pics/hotel/gallery/C__WebServ_Webs_Fundacion_folletos_download_742414550_HardRock%20HardRock%20RockstarSuite_Foyerk.jpg")
                    .size(20L)
                    .build();

            List<Room> luxuryRooms = new ArrayList<>();
            List<Room> superiorRooms = new ArrayList<>();
            List<Room> rockStarRooms = new ArrayList<>();
            int counter = 1;
            while(counter <= 30) {
                if (counter <= 10) {
                    luxuryRooms.add(Room.builder()
                            .category(luxury).build());
                } else if (counter > 10 && counter <=20) {
                    superiorRooms.add(Room.builder()
                            .category(superiorStreetView).build());
                } else {
                    rockStarRooms.add(Room.builder()
                            .category(rockStarSuite).build());
                }

                counter++;
            }
            Reservation reservation = Reservation.builder()
                    .category(luxury)
                    .startDate(LocalDate.of(2020, 6, 10))
                    .endDate(LocalDate.of(2020, 7, 20))
                    .build();

            luxury.setRoom(luxuryRooms);
            superiorStreetView.setRoom(superiorRooms);
            rockStarSuite.setRoom(rockStarRooms);



            categoryRepository.save(luxury);
            categoryRepository.save(superiorStreetView);
            categoryRepository.save(rockStarSuite);
            reservationRepository.save(reservation);
            roomRepository.saveAll(luxuryRooms);
            roomRepository.saveAll(superiorRooms);
            roomRepository.saveAll(rockStarRooms);

            ReservedRoom reservedRoom = ReservedRoom.builder()
                    .room(luxuryRooms.get(0))
                    .reservation(reservation)
                    .build();

            reservedRoomRepository.save(reservedRoom);
        };

    }

}
