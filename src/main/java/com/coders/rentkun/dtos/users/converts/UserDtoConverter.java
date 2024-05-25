package com.coders.rentkun.dtos.users.converts;

import com.coders.rentkun.dtos.users.requests.UserRegisterRequestDto;
import com.coders.rentkun.dtos.users.responses.CurrentUserResponseDto;
import com.coders.rentkun.dtos.users.responses.UpdatedEmailAndPhoneNumberResponseDto;
import com.coders.rentkun.entities.users.Authority;
import com.coders.rentkun.entities.users.Role;
import com.coders.rentkun.entities.users.User;
import com.coders.rentkun.entities.users.UserInfos;
import com.coders.rentkun.services.AuthorityService;
import com.coders.rentkun.services.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserDtoConverter {
    private final PasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;
    private final RoleService roleService;

    public UserDtoConverter(PasswordEncoder passwordEncoder, AuthorityService authorityService, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
        this.roleService = roleService;
    }

    public User convertToEntity(UserRegisterRequestDto requestDto, UserInfos foundUserInfos) {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityService.findByName("CREATE"));
        authorities.add(authorityService.findByName("READ"));
        authorities.add(authorityService.findByName("UPDATE"));
        authorities.add(authorityService.findByName("DELETE"));

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRole("ROLE_USER"));

        User user = new User(
                requestDto.getEmail(),
                passwordEncoder.encode(requestDto.getPassword()),
                foundUserInfos,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        user.setRoles(roles);
        user.setAuthorities(authorities);

        return user;
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
