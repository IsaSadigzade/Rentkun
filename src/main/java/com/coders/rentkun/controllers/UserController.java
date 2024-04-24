package com.coders.rentkun.controllers;

import com.coders.rentkun.core.utilities.results.DataResult;
import com.coders.rentkun.core.utilities.results.Result;
import com.coders.rentkun.dtos.users.requests.*;
import com.coders.rentkun.dtos.users.responses.CurrentUserResponseDto;
import com.coders.rentkun.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Result registerUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        return userService.registerUser(userRegisterRequestDto);
    }

    @PostMapping("/login")
    public Result loginUser(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return userService.loginUser(userLoginRequestDto);
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
    public Result updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return userService.updateUser(userId, userUpdateRequestDto);
    }

    @DeleteMapping("/delete-user/{userId}")
    public Result deleteUserById(@PathVariable Long userId) {
        //TODO: password and confirm password will added here for successful deleting account
        return userService.deleteUser(userId);
    }

    @PutMapping("/update-password/{userId}")
    public Result updatePassword(@PathVariable Long userId, @RequestBody UserPasswordUpdateRequestDto userPasswordUpdateRequestDto) {
        return userService.updatePassword(userId, userPasswordUpdateRequestDto);
    }

    @PutMapping("/update-email-and-phone-number/{userId}")
    public Result updateEmailAndPhoneNumber(@PathVariable Long userId, @RequestBody UserEmailAndPhoneNumberUpdateRequestDto userEmailAndPhoneNumberUpdateRequestDto) {
        return userService.updateEmailAndPhoneNumber(userId, userEmailAndPhoneNumberUpdateRequestDto);
    }

    @PostMapping("/image-upload/{userId}")
    public ResponseEntity<?> uploadImageToFIleSystem(@PathVariable Long userId, @RequestParam("image") MultipartFile file) {
        String uploadImage = userService.uploadImageToFileSystem(userId, file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/image-download/{userId}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable Long userId) {
        byte[] imageData=userService.downloadImageFromFileSystem(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }

}
