package com.coders.rentkun.repositories;

import com.coders.rentkun.entities.users.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
