package com.meo.mp3.controllers;

import com.meo.mp3.models.users.account.User;
import com.meo.mp3.security.jwt.JwtResponse;
import com.meo.mp3.services.JwtService;
import com.meo.mp3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class HomeController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String message = "";
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        message = "Login success";
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByEmail(user.getEmail());
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(),
                userDetails.getUsername(), currentUser.getProfile().getFirstName(),
                currentUser.getProfile().getLastName(),
                userDetails.getAuthorities()));
    }

}
