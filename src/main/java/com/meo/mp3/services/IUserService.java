package com.meo.mp3.services;

import com.meo.mp3.models.users.account.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    void save (User user);
    Iterable<User> findAll();
    User findByEmail(String email);
}
