package com.coders.rentkun.dtos.users.converts;

import com.coders.rentkun.dtos.users.requests.UserRegisterRequestDto;
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
                LocalDateTime.now(),
                LocalDateTime.now(),
                foundUserInfos
        );
    }
}
