package com.coders.rentkun.repositories;

import com.coders.rentkun.entities.users.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
}
