package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.entity.HotelUser;
import com.codecool.hotel_backend.repository.UserRepository;
import com.codecool.hotel_backend.security.JwtTokenServices;
import org.springframework.stereotype.Component;

@Component
public class ControllerUtil {
    UserRepository userRepository;
    JwtTokenServices jwtTokenServices;

    public ControllerUtil(UserRepository userRepository, JwtTokenServices jwtTokenServices) {
        this.userRepository = userRepository;
        this.jwtTokenServices = jwtTokenServices;
    }

    public HotelUser getUserFromToken(String authorizationString) {
        String token = jwtTokenServices.getTokenFromRequestHeaderAuthorization(authorizationString);
        String loggedInUserName = jwtTokenServices.getUsernameFromToken(token);
        return userRepository.getHotelUserByUsername(loggedInUserName);
    }

}
