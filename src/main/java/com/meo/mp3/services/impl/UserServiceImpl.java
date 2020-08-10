package com.meo.mp3.services.impl;

import com.meo.mp3.models.users.account.Profile;
import com.meo.mp3.models.users.account.Role;
import com.meo.mp3.models.users.account.User;
import com.meo.mp3.repositories.ProfileRepository;
import com.meo.mp3.repositories.UserRepository;
import com.meo.mp3.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public User signUp(User user) {
        Role role = new Role();
        role.setId(1l);
        role.setPermission("ROLE_MEMBER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRole((Role) roleSet);
        user.setProfile(profileRepository.save(new Profile()));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
