package com.codecool.hotel_backend.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtTokenServices {

    private Key secretKey;

    @PostConstruct
    private void initKey() {
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public UsernamePasswordAuthenticationToken validateTokenAndExtractUserSpringToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        ArrayList<String> rolesList = claims.get("roles", ArrayList.class);
        List<SimpleGrantedAuthority> roles =
                rolesList.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                null,
                roles);
    }

    public String generateToken(Authentication authentication) {
        List<String> roles = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        int jwtExpirationMinutes = 120000;
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * jwtExpirationMinutes))
                .signWith(secretKey)
                .compact();
    }

}
