package com.meo.mp3.services.impl;

import com.meo.mp3.exception.Mp3Exception;
import com.meo.mp3.models.users.account.Profile;
import com.meo.mp3.models.users.account.Role;
import com.meo.mp3.models.users.account.User;
import com.meo.mp3.models.users.account.UserPrinciple;
import com.meo.mp3.repositories.UserRepository;
import com.meo.mp3.request.UserRequestModel;
import com.meo.mp3.services.IProfileService;
import com.meo.mp3.services.IRoleService;
import com.meo.mp3.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IProfileService profileService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User signUp(UserRequestModel requestModel) {
        if (userRepository.findByEmail(requestModel.getEmail()) != null) {
            throw new Mp3Exception(HttpStatus.BAD_REQUEST, "Email have been existed!");
        }

        User user = new User(requestModel.getEmail(), requestModel.getPassword());

        Role role = new Role();
        Profile profile = new Profile();
        profile.setAvatarUrl("https://imgt.taimienphi.vn/cf/Images/huy/2020/3/19/hinh-avatar-cho-nu-dep-1.jpg");
        profile.setFirstName(requestModel.getFirstName());
        profile.setLastName(requestModel.getLastName());
        role.setId(1L);
        role.setPermission("ROLE_MEMBER");
        user.setRole(roleService.save(role));
        user.setProfile(profileService.save(profile));
        user.setPassword(passwordEncoder.encode(requestModel.getPassword()));

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return UserPrinciple.build(user);
    }
}