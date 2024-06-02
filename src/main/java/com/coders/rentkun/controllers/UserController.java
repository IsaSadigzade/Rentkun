package com.coders.rentkun.controllers;

import com.coders.rentkun.core.utilities.results.DataResult;
import com.coders.rentkun.core.utilities.results.Result;
import com.coders.rentkun.core.utilities.results.SuccessDataResult;
import com.coders.rentkun.dtos.users.requests.UserDetailsRequestDto;
import com.coders.rentkun.dtos.users.requests.UserEmailAndPhoneNumberUpdateRequestDto;
import com.coders.rentkun.dtos.users.requests.UserPasswordUpdateRequestDto;
import com.coders.rentkun.dtos.users.responses.CurrentUserResponseDto;
import com.coders.rentkun.dtos.users.responses.UpdatedEmailAndPhoneNumberResponseDto;
import com.coders.rentkun.services.users.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
