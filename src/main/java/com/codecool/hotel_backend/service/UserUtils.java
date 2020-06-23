package com.codecool.hotel_backend.service;

import antlr.StringUtils;
import com.codecool.hotel_backend.entity.HotelUser;
import com.codecool.hotel_backend.entity.UserCredentials;
import com.codecool.hotel_backend.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserUtils {

    private final UserRepository userRepository;

    public UserUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(UserCredentials data) {
        String userName = data.getUsername();
        String password = data.getPassword();
        System.out.println(userName);

        // First check for invalid input
        char[] illegalCharacterList = {'.', ',', '+', '-', '[', ']', '{', '}', ' ', '"', '\''};
        if (userName == null || userName.length() < 4) return "Username is too short";
        if (userName.length() > 20) return "Username is too long";
        if (password == null || password.length() < 4) return "Password is too short";
        if (password.length() > 20) return "Password is too long";
        if (Character.isDigit(userName.charAt(0))) return "First character can't be a number";

        for (char c : illegalCharacterList) {
            for (int i=0; i<userName.length(); i++) {
                if (userName.charAt(i) == c) return "Username contains illegal characters";
            }
        }

        List<HotelUser> userList = userRepository.findAll();
        for (HotelUser hotelUser : userList) {

        }


        return "Successful Registration";

    }
}
