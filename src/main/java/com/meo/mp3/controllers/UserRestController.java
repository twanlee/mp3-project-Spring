package com.meo.mp3.controllers;

import com.meo.mp3.models.users.account.Profile;
import com.meo.mp3.models.users.account.User;
import com.meo.mp3.services.IProfileService;
import com.meo.mp3.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserRestController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProfileService profileService;

    @GetMapping("/userList")
    public Iterable<User> findAll() {
        return userService.findAll();
    }
    @GetMapping("/{id}/profile")
    public Profile getById(@PathVariable ("id") Long id) {
        User user = userService.findById(id);
        return profileService.findById(user.getProfile().getId());
    }
    @PutMapping("/{id}/edit/profile")
    public User updateProfile(@PathVariable Long id, @RequestBody User user) {
//        Profile currentProfile = profileService.findById(id);
//        currentUser.setProfile(user.getProfile());
        return null;
    }
}
