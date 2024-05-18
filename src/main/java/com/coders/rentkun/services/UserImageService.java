package com.coders.rentkun.services;

import com.coders.rentkun.repositories.UserImageRepository;
import org.springframework.stereotype.Service;

@Service
public class UserImageService {
    private final UserImageRepository userImageRepository;

    public UserImageService(UserImageRepository userImageRepository) {
        this.userImageRepository = userImageRepository;
    }
}
