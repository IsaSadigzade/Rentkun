package com.coders.rentkun.services;

import com.coders.rentkun.dtos.users.converts.UserDtoConverter;
import com.coders.rentkun.dtos.users.requests.UserLoginRequestDto;
import com.coders.rentkun.dtos.users.requests.UserRegisterRequestDto;
import com.coders.rentkun.dtos.users.responses.CurrentUserResponseDto;
import com.coders.rentkun.entities.users.UserInfos;
import com.coders.rentkun.repositories.UserRepository;
import com.coders.rentkun.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserInfosService userInfosService;
    private final UserDtoConverter userDtoConverter;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository, UserInfosService userInfosService, UserDtoConverter userDtoConverter) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.userInfosService = userInfosService;
        this.userDtoConverter = userDtoConverter;
    }

    @Transactional
    public CurrentUserResponseDto register(UserRegisterRequestDto request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BadCredentialsException("Password and confirmation password don't matching.");
        }

        UserInfos savedUserInfos = userInfosService.save(request.getUserDetails());
        UserInfos foundUserInfos = userInfosService.findByUserId(savedUserInfos.getId());

        CurrentUserResponseDto userResponse = userDtoConverter.convertToResponse(
                userRepository.save(userDtoConverter.convertToEntity(request, foundUserInfos))
        );

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        userResponse.setToken(jwtTokenProvider.generateToken(authentication));

        return userResponse;
    }

    public String login(UserLoginRequestDto request) throws AuthenticationException {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            Authentication auth = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return jwtTokenProvider.generateToken(auth);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid email or password");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during login");
        }
    }
}

