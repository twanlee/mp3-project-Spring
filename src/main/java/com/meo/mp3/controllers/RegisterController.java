package com.meo.mp3.controllers;

import com.meo.mp3.exception.Mp3Exception;
import com.meo.mp3.models.users.account.User;
import com.meo.mp3.request.UserRequestModel;
import com.meo.mp3.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class RegisterController extends BaseController {
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestModel user) {
        try {
            success(userService.signUp(user));
        } catch (Mp3Exception ex) {
            unSuccess(ex);
        }
        return ResponseEntity.status(status).body(body);
    }
}
