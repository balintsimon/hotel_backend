package com.codecool.hotel_backend.controller;

import com.codecool.hotel_backend.entity.HotelUser;
import com.codecool.hotel_backend.entity.UserCredentials;
import com.codecool.hotel_backend.security.JwtTokenServices;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenServices jwtTokenServices;
    private final ControllerUtil controllerUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, ControllerUtil controllerUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
        this.controllerUtil = controllerUtil;
    }

    @PostMapping(value = "/signin")
    public ResponseEntity signIn(@RequestBody UserCredentials data) {
        Map<Object, Object> model = new HashMap<>();
        try {
            String username = data.getUsername();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(username, roles);

            model.put("username", username);
            model.put("roles", roles);
            model.put("token", token);
            model.put("status","DONE");
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            model.put("status", "WRONG");
            return ResponseEntity.ok(model);
        }
    }

    @GetMapping("/get-user-from-token")
    public HotelUser returnHotelUserFromToken(@RequestHeader String Authorization) {
        return controllerUtil.getUserFromToken(Authorization);
        //        return getUserFromToken(reqHeader);
    }
}
