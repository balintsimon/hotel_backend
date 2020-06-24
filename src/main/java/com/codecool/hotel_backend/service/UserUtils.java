package com.codecool.hotel_backend.service;

import antlr.StringUtils;
import com.codecool.hotel_backend.entity.HotelUser;
import com.codecool.hotel_backend.entity.UserCredentials;
import com.codecool.hotel_backend.repository.UserRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class UserUtils {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public String registerUser(UserCredentials data) {
        String userName = data.getUsername();
        String password = data.getPassword();

        // First check for invalid input
        char[] illegalCharacterList = {'.', ',', '+', '-', '[', ']', '{', '}', ' ', '"', '\'', '/', '\\'};
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
            if (hotelUser.getUsername().equals(userName)) {
                return "Username already taken";
            }
        }

        HotelUser user = HotelUser.builder()
                .username(userName)
                .password(passwordEncoder.encode(password))
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        userRepository.save(user);

        return "Successful Registration";

    }
}
