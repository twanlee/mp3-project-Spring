package com.meo.mp3.services;

import com.meo.mp3.models.users.account.Role;

public interface IRoleService {
    Role findRoleByPermission(String rolePermission);
    Iterable<Role> findAll();
    Role save(Role role);
}
