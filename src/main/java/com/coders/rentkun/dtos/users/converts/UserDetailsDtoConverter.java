package com.coders.rentkun.dtos.users.converts;

import com.coders.rentkun.dtos.users.requests.UserDetailsRequestDto;
import com.coders.rentkun.entities.users.UserInfos;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserDetailsDtoConverter {

    public UserInfos convertToEntity(UserDetailsRequestDto request) {
        return new UserInfos(
                request.getFirstName(),
                request.getLastName(),
                request.getPhoneNumber(),
                request.getGender(),
                request.getCityAndZipCode(),
                request.getLocation(),
                request.getBirthDate(),
                LocalDate.now(),
                LocalDate.now()
        );
    }

    public UserInfos convertToEntity(UserInfos foundUserInfos, UserDetailsRequestDto request) {
        foundUserInfos.setFirstName(request.getFirstName());
        foundUserInfos.setLastName(request.getLastName());
        foundUserInfos.setPhoneNumber(request.getPhoneNumber());
        foundUserInfos.setGender(request.getGender());
        foundUserInfos.setCityAndZipCode(request.getCityAndZipCode());
        foundUserInfos.setLocation(request.getLocation());
        return foundUserInfos;
    }
}
