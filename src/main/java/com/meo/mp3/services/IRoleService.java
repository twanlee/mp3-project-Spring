package com.meo.mp3.services;

import com.meo.mp3.models.users.account.Role;

public interface IRoleService {
    Role findRoleByPermission(String rolePermission);
    Iterable<Role> findAll();
    void save(Role role);
}
