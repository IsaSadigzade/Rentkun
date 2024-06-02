package com.coders.rentkun.services.users;

import com.coders.rentkun.entities.users.Role;
import com.coders.rentkun.entities.users.enums.ERole;
import com.coders.rentkun.repositories.users.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRole(String roleName) {
        return roleRepository.findByName(ERole.valueOf(roleName));
    }
}
