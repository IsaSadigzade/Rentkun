package com.coders.rentkun.controllers;

import com.coders.rentkun.core.utilities.results.DataResult;
import com.coders.rentkun.core.utilities.results.Result;
import com.coders.rentkun.core.utilities.results.SuccessDataResult;
import com.coders.rentkun.dtos.users.requests.*;
import com.coders.rentkun.dtos.users.responses.CurrentUserResponseDto;
import com.coders.rentkun.dtos.users.responses.UpdatedEmailAndPhoneNumberResponseDto;
import com.coders.rentkun.security.JwtTokenProvider;
import com.coders.rentkun.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<DataResult<String>> registerUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        userService.registerUser(userRegisterRequestDto);
        return ResponseEntity.ok(new SuccessDataResult<>("User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<Result> loginUser(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        SecurityContextHolder.getContext().setAuthentication(userService.loginUser(userLoginRequestDto));
        return ResponseEntity.ok(new SuccessDataResult<>(jwtTokenProvider.generateToken(userService.loginUser(userLoginRequestDto))));
    }

    @GetMapping
    public DataResult<List<CurrentUserResponseDto>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get-user/{userId}")
    public DataResult<CurrentUserResponseDto> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/update-user/{userId}")
    public Result updateUser(@PathVariable Long userId, @RequestBody UserDetailsRequestDto userDetailsRequestDto) {
        return new SuccessDataResult<>(userService.updateUser(userId, userDetailsRequestDto));
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        //TODO: password and confirm password will added here for successful deleting account
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-password/{userId}")
    public Result updatePassword(@PathVariable Long userId, @RequestBody UserPasswordUpdateRequestDto userPasswordUpdateRequestDto) {
        return userService.updatePassword(userId, userPasswordUpdateRequestDto);
    }

    @PutMapping("/update-email-and-phone-number/{userId}")
    public UpdatedEmailAndPhoneNumberResponseDto updateEmailAndPhoneNumber(@PathVariable Long userId, @RequestBody UserEmailAndPhoneNumberUpdateRequestDto userEmailAndPhoneNumberUpdateRequestDto) {
        return userService.updateEmailAndPhoneNumber(userId, userEmailAndPhoneNumberUpdateRequestDto);
    }

//    @PostMapping("/image-upload/{userId}")
//    public ResponseEntity<?> uploadImageToFIleSystem(@PathVariable Long userId, @RequestParam("image") MultipartFile file) {
//        String uploadImage = userService.uploadImageToFileSystem(userId, file);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(uploadImage);
//    }
//
//    @GetMapping("/image-download/{userId}")
//    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable Long userId) {
//        byte[] imageData=userService.downloadImageFromFileSystem(userId);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(imageData);
//
//    }

}
