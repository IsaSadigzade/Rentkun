package com.coders.rentkun.services;

import com.coders.rentkun.dtos.users.converts.UserDetailsDtoConverter;
import com.coders.rentkun.dtos.users.requests.CreateUserDetailsDto;
import com.coders.rentkun.entities.users.UserInfos;
import com.coders.rentkun.repositories.UserInfosRepository;
import org.springframework.stereotype.Service;

@Service
public class UserInfosService {
    private final UserInfosRepository userInfosRepository;
    private final UserDetailsDtoConverter userDetailsDtoConverter;

    public UserInfosService(UserInfosRepository userInfosRepository, UserDetailsDtoConverter userDetailsDtoConverter) {
        this.userInfosRepository = userInfosRepository;
        this.userDetailsDtoConverter = userDetailsDtoConverter;
    }

    public UserInfos save(CreateUserDetailsDto userDetails) {
        return userInfosRepository.save(
                userDetailsDtoConverter.convertToEntity(userDetails)
        );
    }

    protected UserInfos findByUserId(Long userId) {
        return userInfosRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("UserInfos not found with user id: " + userId));
    }
}
