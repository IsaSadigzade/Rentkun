package com.coders.rentkun.dtos.users.converts;

import com.coders.rentkun.dtos.users.requests.UserEmailAndPhoneNumberUpdateRequestDto;
import com.coders.rentkun.dtos.users.requests.UserRegisterRequestDto;
import com.coders.rentkun.dtos.users.responses.CurrentUserResponseDto;
import com.coders.rentkun.dtos.users.responses.UpdatedEmailAndPhoneNumberResponseDto;
import com.coders.rentkun.entities.users.Role;
import com.coders.rentkun.entities.users.User;
import com.coders.rentkun.entities.users.UserInfos;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserDtoConverter {
    private final PasswordEncoder passwordEncoder;

    public UserDtoConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User convertToEntity(UserRegisterRequestDto requestDto, UserInfos foundUserInfos) {
        return new User(
                requestDto.getEmail(),
                passwordEncoder.encode(requestDto.getPassword()),
                Role.ROLE_USER,
                LocalDateTime.now(),
                LocalDateTime.now(),
                foundUserInfos
        );
    }

    public User convertToEntity(User foundUser, UserInfos foundUserInfos) {
        foundUser.setUserInfos(foundUserInfos);
        return foundUser;
    }

    public CurrentUserResponseDto convertToResponse(User entity) {
        return new CurrentUserResponseDto(
                entity.getId(),
                entity.getUserInfos().getFirstName(),
                entity.getUserInfos().getLastName(),
                entity.getUserInfos().getPhoneNumber(),
                entity.getEmail(),
                entity.getUserInfos().getGender(),
                entity.getUserInfos().getCityAndZipCode(),
                entity.getUserInfos().getLocation(),
                entity.getUserInfos().getDateOfBirth()
        );
    }

    public UpdatedEmailAndPhoneNumberResponseDto convertToEmailAndPhoneNumberResponse(User entity) {
        return new UpdatedEmailAndPhoneNumberResponseDto(
                entity.getId(),
                entity.getEmail(),
                entity.getUserInfos().getPhoneNumber()
        );
    }
}
