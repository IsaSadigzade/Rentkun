package com.coders.rentkun.dtos.users.converts;

import com.coders.rentkun.dtos.users.requests.UserRegisterRequestDto;
import com.coders.rentkun.dtos.users.responses.CurrentUserResponseDto;
import com.coders.rentkun.dtos.users.responses.UpdatedEmailAndPhoneNumberResponseDto;
import com.coders.rentkun.entities.users.*;
import com.coders.rentkun.entities.users.enums.EAuthority;
import com.coders.rentkun.entities.users.enums.ERole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserDtoConverter {
    private final PasswordEncoder passwordEncoder;

    public UserDtoConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User convertToEntity(UserRegisterRequestDto requestDto, UserInfos foundUserInfos) {
        Authority createAuthority = Authority.builder()
                .name(EAuthority.CREATE)
                .build();
        Authority readAuthority = Authority.builder()
                .name(EAuthority.READ)
                .build();
        Authority updateAuthority = Authority.builder()
                .name(EAuthority.UPDATE)
                .build();
        Authority deleteAuthority = Authority.builder()
                .name(EAuthority.DELETE)
                .build();
        Set<Authority> authorities = new HashSet<>();
        authorities.add(createAuthority);
        authorities.add(readAuthority);
        authorities.add(updateAuthority);
        authorities.add(deleteAuthority);

        Role userRole = Role.builder()
                .name(ERole.ROLE_USER)
                .build();

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        return new User(
                requestDto.getEmail(),
                passwordEncoder.encode(requestDto.getPassword()),
                roles,
                authorities,
                foundUserInfos,
                LocalDateTime.now(),
                LocalDateTime.now()
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
