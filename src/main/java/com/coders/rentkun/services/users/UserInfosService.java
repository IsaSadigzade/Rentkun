package com.coders.rentkun.services.users;

import com.coders.rentkun.core.utilities.results.ErrorResult;
import com.coders.rentkun.dtos.users.converts.UserDetailsDtoConverter;
import com.coders.rentkun.dtos.users.requests.UserDetailsRequestDto;
import com.coders.rentkun.dtos.users.requests.UserEmailAndPhoneNumberUpdateRequestDto;
import com.coders.rentkun.entities.users.UserInfos;
import com.coders.rentkun.repositories.users.UserInfosRepository;
import org.springframework.stereotype.Service;

@Service
public class UserInfosService {
    private final UserInfosRepository userInfosRepository;
    private final UserDetailsDtoConverter userDetailsDtoConverter;

    public UserInfosService(UserInfosRepository userInfosRepository, UserDetailsDtoConverter userDetailsDtoConverter) {
        this.userInfosRepository = userInfosRepository;
        this.userDetailsDtoConverter = userDetailsDtoConverter;
    }

    public UserInfos save(UserDetailsRequestDto userDetails) {
        return userInfosRepository.save(
                userDetailsDtoConverter.convertToEntity(userDetails)
        );
    }

    public void updateUserInfos(Long userInfosId, UserDetailsRequestDto userDetailsRequestDto) {
        userInfosRepository.save(
                userDetailsDtoConverter.convertToEntity(findByUserId(userInfosId), userDetailsRequestDto)
        );
    }

    public void updatePhoneNumber(Long userInfosId, UserEmailAndPhoneNumberUpdateRequestDto userEmailAndPhoneNumberUpdateRequestDto) {
        UserInfos foundUserInfos = findByUserId(userInfosId);
        foundUserInfos.setPhoneNumber(userEmailAndPhoneNumberUpdateRequestDto.getPhoneNumber());
        userInfosRepository.save(foundUserInfos);
    }

    public void deleteUserInfos(Long userId) {
        if (isUserInfosExistById(userId)) {
            new ErrorResult("User Infos not found.");
        }
        userInfosRepository.deleteById(userId);
    }

    protected UserInfos findByUserId(Long userId) {
        return userInfosRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("UserInfos not found with user id: " + userId));
    }

    private boolean isUserInfosExistById(Long userId) {
        return userInfosRepository.existsById(userId);
    }
}
