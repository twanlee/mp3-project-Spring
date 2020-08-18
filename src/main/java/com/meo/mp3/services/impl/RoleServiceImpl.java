package com.meo.mp3.services.impl;

import com.meo.mp3.models.users.account.Role;
import com.meo.mp3.repositories.RoleRepository;
import com.meo.mp3.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findRoleByPermission(String rolePermission) {
        return roleRepository.findRoleByPermission(rolePermission);
    }

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
