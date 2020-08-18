package com.meo.mp3.services;

import com.meo.mp3.models.users.account.User;
import com.meo.mp3.request.UserRequestModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save (User user);
    Iterable<User> findAll();
    User findByEmail(String email);
    User findById(Long id);
    User signUp(UserRequestModel user);
}
