package com.meo.mp3.controllers;

import com.meo.mp3.models.users.account.User;
import com.meo.mp3.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody User user){
        userService.signUp(user);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
