package com.meo.mp3.repositories;

import com.meo.mp3.models.users.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByPermission(String rolePermission);
}
