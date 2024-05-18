package com.coders.rentkun.dtos.users.converts;

import com.coders.rentkun.dtos.users.requests.CreateUserDetailsDto;
import com.coders.rentkun.entities.users.UserInfos;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserDetailsDtoConverter {

    public UserInfos convertToEntity(CreateUserDetailsDto request) {
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
}
