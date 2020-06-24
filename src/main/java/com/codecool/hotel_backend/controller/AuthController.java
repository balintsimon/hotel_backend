package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.entity.UserCredentials;
import com.codecool.hotel_backend.security.JwtTokenServices;
import com.codecool.hotel_backend.service.UserUtils;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;

    private final UserUtils userUtils;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, UserUtils userUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
        this.userUtils = userUtils;
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<String> signIn(@RequestBody UserCredentials data, HttpServletResponse response) {
        try {
            String username = data.getUsername();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(username, roles);

            addTokenToCookie(response,token);
            System.out.println(roles);
            return ResponseEntity.ok().body(data.getUsername());
        } catch (AuthenticationException e) {
            return ResponseEntity.ok().body("WRONG");
        }
    }

    @PostMapping(value = "/register-user")
    public String registration(@RequestBody UserCredentials data) {
        String response = userUtils.registerUser(data);
        return "{\"response\": \"" + response + "\"}"; // manual json, Look for modules
    }

    private void addTokenToCookie(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie.from("token", token)
                .domain("localhost") // should be parameterized
                .sameSite("Strict")  // CSRF
//                .secure(true)
                .maxAge(Duration.ofHours(24))
                .httpOnly(true)      // XSS
                .path("/")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }
}
