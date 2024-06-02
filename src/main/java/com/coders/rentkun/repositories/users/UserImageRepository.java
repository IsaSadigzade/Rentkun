package com.coders.rentkun.repositories.users;

import com.coders.rentkun.entities.users.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
    Optional<UserImage> findByName(String userImageName);
}
