package com.coders.rentkun.repositories;

import com.coders.rentkun.entities.users.UserInfos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfosRepository extends JpaRepository<UserInfos, Long> {
}
