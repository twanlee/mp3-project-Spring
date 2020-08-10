package com.meo.mp3.services.Impl;

import com.meo.mp3.models.users.account.Role;
import com.meo.mp3.repositories.RoleRepository;
import com.meo.mp3.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    public RoleServiceImpl() {}
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public Role findRoleByPermission(String rolePermission) {
        return roleRepository.findRoleByPermission(rolePermission);
    }

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
