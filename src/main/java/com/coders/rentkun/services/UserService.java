package com.coders.rentkun.services;

import com.coders.rentkun.core.utilities.results.DataResult;
import com.coders.rentkun.core.utilities.results.Result;
import com.coders.rentkun.dtos.users.requests.*;
import com.coders.rentkun.dtos.users.responses.CurrentUserResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    Result registerUser(UserRegisterRequestDto userRegisterRequestDto);

    DataResult<List<CurrentUserResponseDto>> getAllUsers();

    Result loginUser(UserLoginRequestDto userLoginRequestDto);
//    Result logout();
    Result updatePassword(Long userId, UserPasswordUpdateRequestDto userPasswordUpdateRequestDto);
//    DataResult<List<User>> getAllUsersByRole(String role);
    DataResult<CurrentUserResponseDto> getUserById(Long id);
//    DataResult<CurrentUserResponseDto> getUserByEmail(String email);
//    DataResult<CurrentUserResponseDto> getUserByEmailOrPhone(String email, String phone);
//    DataResult<CurrentUserResponseDto> getUserByPhone(String phone);
    Result updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto);
    Result deleteUser(Long userId);

    Result updateEmailAndPhoneNumber(Long userId, UserEmailAndPhoneNumberUpdateRequestDto userEmailAndPhoneNumberUpdateRequestDto);

    String uploadImageToFileSystem(Long userId, MultipartFile file);

    byte[] downloadImageFromFileSystem(Long userId);
}
