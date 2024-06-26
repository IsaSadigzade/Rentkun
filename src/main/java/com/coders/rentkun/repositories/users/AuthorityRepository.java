package com.coders.rentkun.repositories.users;

import com.coders.rentkun.entities.users.Authority;
import com.coders.rentkun.entities.users.enums.EAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    boolean existsByName(EAuthority name);

    Authority findByName(EAuthority name);
}
