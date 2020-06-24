package com.codecool.hotel_backend.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

//public class JwtTokenFilter extends GenericFilterBean {
public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenServices jwtTokenServices;
    public static final String TOKEN = "token";

    JwtTokenFilter(JwtTokenServices jwtTokenServices) {
        this.jwtTokenServices = jwtTokenServices;
    }

    // this is called for every request that comes in (unless its filtered out before in the chain)
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
//        String token = jwtTokenServices.getTokenFromRequest((HttpServletRequest) req);
//        if (token != null && jwtTokenServices.validateToken(token)) {
//            Authentication auth = jwtTokenServices.parseUserFromTokenInfo(token);
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
//        // process the next filter.
//        filterChain.doFilter(req, res);
//    }

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        Optional<Cookie> jwtToken =
//                Arrays.stream(Optional.ofNullable(request.get .getCookies()).orElse(new Cookie[]{}))
//                        .filter(cookie -> cookie.getName().equals(TOKEN))
//                        .findFirst();
//        String tokenString = jwtToken.toString();
//        if (jwtToken.isPresent() && jwtTokenServices.validateToken(tokenString)) {
//            Authentication auth = jwtTokenServices.parseUserFromTokenInfo(tokenString);
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
//        // process the next filter.
//        filterChain.doFilter(request, response);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Optional<Cookie> jwtToken =
                Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[]{}))
                        .filter(cookie -> cookie.getName().equals(TOKEN))
                        .findFirst();
        if (jwtToken.isPresent()) {
//            UsernamePasswordAuthenticationToken userToken = jwtTokenServices.validateTokenAndExtractUserSpringToken(jwtToken.get().getValue());
            Authentication auth = jwtTokenServices.parseUserFromTokenInfo(jwtToken.toString());
            SecurityContextHolder.getContext().setAuthentication(auth);

        }
        filterChain.doFilter(request, response);
    }
}
