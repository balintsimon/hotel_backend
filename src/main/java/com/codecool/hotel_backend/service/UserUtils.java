package com.codecool.hotel_backend.service;

import com.codecool.hotel_backend.entity.UserCredentials;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {


    public String registerUser(UserCredentials data) {
        String userName = data.getUsername();
        String password = data.getPassword();

        // First check for invalid input
        if (userName == null || userName.length() < 4) return "Username is too short";
        if (password == null || password.length() < 4) return "Password is too short";




        return "Successful Registration";

    }
}
