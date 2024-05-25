package com.coders.rentkun.repositories;

import com.coders.rentkun.entities.users.Role;
import com.coders.rentkun.entities.users.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    boolean existsByName(ERole name);
}
