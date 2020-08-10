package com.meo.mp3.services;

import com.meo.mp3.models.users.account.User;

public interface UserService extends IService<User> {
    User singUp(User user);
}
