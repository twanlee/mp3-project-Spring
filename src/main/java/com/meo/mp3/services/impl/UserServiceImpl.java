package com.meo.mp3.services.impl;

import com.meo.mp3.models.artist.Artist;
import com.meo.mp3.models.users.account.User;
import com.meo.mp3.repositories.UserRepository;
import com.meo.mp3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User save(User model) {
        return userRepository.save(model);
    }

    @Override
    public User delete(Long id) {
        User user = findById(id);
        userRepository.deleteById(id);
        return user;
    }

    @Override
    public User singUp(User user) {
        return null;
    }
}
