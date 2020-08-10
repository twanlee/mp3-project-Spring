package com.meo.mp3.repositories;

import com.meo.mp3.models.users.account.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
