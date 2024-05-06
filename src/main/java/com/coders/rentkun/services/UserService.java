package com.coders.rentkun.services;

import com.coders.rentkun.core.utilities.results.*;
import com.coders.rentkun.dtos.users.requests.*;
import com.coders.rentkun.dtos.users.responses.CurrentUserResponseDto;
import com.coders.rentkun.entities.users.User;
import com.coders.rentkun.entities.users.UserDetails;
import com.coders.rentkun.entities.users.UserImage;
import com.coders.rentkun.repositories.UserDetailsRepository;
import com.coders.rentkun.repositories.UserImageRepository;
import com.coders.rentkun.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final UserImageRepository userImageRepository;

    public UserService(UserRepository userRepository, UserDetailsRepository userDetailsRepository, UserImageRepository userImageRepository) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.userImageRepository = userImageRepository;
    }

    @Transactional
    public Result registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        if (!userRegisterRequestDto.getPassword().equals(userRegisterRequestDto.getConfirmPassword())) {
            return new ErrorResult("Password and confirmation password don't matching.");
        }
        User user = new User();
        user.setEmail(userRegisterRequestDto.getEmail());
        user.setPassword(userRegisterRequestDto.getPassword());

        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(userRegisterRequestDto.getFirstName());
        userDetails.setLastName(userRegisterRequestDto.getLastName());
//        userDetails.setEmail(userRegisterRequestDto.getEmail());
        userDetails.setPhoneNumber(userRegisterRequestDto.getPhoneNumber());
        userDetails.setGender(userRegisterRequestDto.getGender());
        userDetails.setCityAndZipCode(userRegisterRequestDto.getCityAndZipCode());
        userDetails.setLocation(userRegisterRequestDto.getLocation());
        userDetails.setDateOfBirth(userRegisterRequestDto.getBirthDate());
        userDetails.setUser(user);
        user.setUserDetails(userDetails);

        userDetailsRepository.save(userDetails);
//        userImageRepository.save(userImage);
        userRepository.save(user);

        return new SuccessResult("User registered successfully");
    }


    public DataResult<List<CurrentUserResponseDto>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<CurrentUserResponseDto> foundUsers = new ArrayList<>();
        for (User user : users) {
            CurrentUserResponseDto currentUserResponseDto = entityToResponse(user);
//            currentUserResponseDto.setImage(downloadImageFromFileSystem(user.getUserImage().getId()));
            foundUsers.add(currentUserResponseDto);
        }
        return new SuccessDataResult<>(foundUsers, "Data listed successfully.");
    }

    public Result loginUser(UserLoginRequestDto userLoginRequestDto) {
        String email = userLoginRequestDto.getEmail();
        String password = userLoginRequestDto.getPassword();

        User loggedUser = userRepository.findByEmail(email);

        if (loggedUser == null || !password.equals(loggedUser.getPassword())) {
            return new ErrorResult("Incorrect email or password.");
        }

        return new SuccessResult("User logged in successfully.");
    }

    public Result updatePassword(Long userId, UserPasswordUpdateRequestDto userPasswordUpdateRequestDto) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isEmpty()) {
            return new ErrorResult("User not found.");
        }
        User user = foundUser.get();
        if (!userPasswordUpdateRequestDto.getOldPassword().equals(user.getPassword())) {
            return new ErrorResult("Old password does not match.");
        }
        if (user.getPassword().equals(userPasswordUpdateRequestDto.getNewPassword())) {
            return new ErrorResult("New password and old password cannot be the same.");
        }
        user.setPassword(userPasswordUpdateRequestDto.getNewPassword());
        userRepository.save(user);
        return new SuccessResult("User password updated successfully.");
    }

    public DataResult<CurrentUserResponseDto> getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            CurrentUserResponseDto currentUserResponseDto = entityToResponse(user);
//            currentUserResponseDto.setImage(downloadImageFromFileSystem(user.getUserImage().getId()));
            return new SuccessDataResult<>(currentUserResponseDto, "User with id = " + id + " is present");
        }
        return new ErrorDataResult<>("User not found");
    }

    @Transactional
    public Result updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<UserDetails> optionalUserDetails = userDetailsRepository.findById(user.getUserDetails().getId());
//            Optional<UserImage> optionalUserImage = userImageRepository.findByFeatureId(user.getUserImage().getId());
            if (optionalUserDetails.isPresent()) {
                UserDetails userDetails = optionalUserDetails.get();
                userDetails.setFirstName(userUpdateRequestDto.getFirstName());
                userDetails.setLastName(userUpdateRequestDto.getLastName());
//                userDetails.setEmail(userUpdateRequestDto.getEmail());
                userDetails.setPhoneNumber(userUpdateRequestDto.getPhoneNumber());
                userDetails.setGender(userUpdateRequestDto.getGender());
                userDetails.setCityAndZipCode(userUpdateRequestDto.getCityAndZipCode());
                userDetails.setLocation(userUpdateRequestDto.getLocation());
                userDetails.setDateOfBirth(userUpdateRequestDto.getBirthDate());
                userDetails.setUser(user);
                userDetailsRepository.save(userDetails);
            } else {
                return new ErrorResult("UserDetails not found");
            }

            user.setEmail(userUpdateRequestDto.getEmail());
            userRepository.save(user);

            return new SuccessResult("User updated successfully");
        }
        return new ErrorResult("User not found");
    }

    public Result deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.delete(user);
            return new SuccessResult("User deleted successfully");
        }
        return new ErrorResult("User not found");
    }

    public Result updateEmailAndPhoneNumber(Long userId, UserEmailAndPhoneNumberUpdateRequestDto userEmailAndPhoneNumberUpdateRequestDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(userEmailAndPhoneNumberUpdateRequestDto.getEmail());
            Optional<UserDetails> optionalUserDetails = userDetailsRepository.findById(user.getUserDetails().getId());
            if (optionalUserDetails.isPresent()) {
                UserDetails userDetails = optionalUserDetails.get();
//                userDetails.setEmail(userEmailAndPhoneNumberUpdateRequestDto.getEmail());
                userDetails.setPhoneNumber(userEmailAndPhoneNumberUpdateRequestDto.getPhoneNumber());
                userDetailsRepository.save(userDetails);
            } else {
                return new ErrorResult("UserDetails not found");
            }
            userRepository.save(user);
            return new SuccessResult("User updated successfully");
        }
        return new ErrorResult("User not found");
    }

    public String uploadImageToFileSystem(Long userId, MultipartFile file) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            String newFileName = fileName.replaceAll(fileName, user.getUserDetails().getFirstName() + "_" + user.getUserDetails().getLastName()) + ".png";
            String FOLDER_PATH = "C:\\Users\\isasa\\OneDrive\\Masaüstü\\rentkun\\src\\main\\resources\\images\\user_profile_photos\\";
            String folderPath = FOLDER_PATH + "\\" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            try {
                Path directoryPath = Paths.get(folderPath);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                }

                Path filePath = Paths.get(directoryPath + "\\" + newFileName);
                Files.write(filePath, file.getBytes());

                UserImage userImage = user.getUserImage();
                if (userImage == null) {
                    userImage = new UserImage();
                    userImage.setCreatedAt(LocalDate.now());
                }
                userImage.setName(newFileName);
                userImage.setType(file.getContentType());
                userImage.setFilePath(filePath.toString());
                userImage.setCreatedAt(userImage.getCreatedAt());
                userImage.setUpdatedAt(LocalDate.now());
                userImage.setUser(user);

                userImageRepository.save(userImage);

                user.setUserImage(userImage);
                userRepository.save(user);

            } catch (IOException e) {
                throw new RuntimeException("Error uploading file", e);
            }

            return "File uploaded successfully : " + folderPath + newFileName;
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
    }


    public byte[] downloadImageFromFileSystem(Long userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isEmpty()) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        String filePath = foundUser.get().getUserImage().getFilePath();
        byte[] images;
        try {
            images = Files.readAllBytes(new File(filePath).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return images;
    }

    private static CurrentUserResponseDto
    entityToResponse(User user) {
        CurrentUserResponseDto currentUserDto = new CurrentUserResponseDto();
        currentUserDto.setId(user.getId());
        currentUserDto.setEmail(user.getEmail());
        currentUserDto.setFirstName(user.getUserDetails().getFirstName());
        currentUserDto.setLastName(user.getUserDetails().getLastName());
        currentUserDto.setPhoneNumber(user.getUserDetails().getPhoneNumber());
        currentUserDto.setGender(user.getUserDetails().getGender());
        currentUserDto.setCityAndZipCode(user.getUserDetails().getCityAndZipCode());
        currentUserDto.setLocation(user.getUserDetails().getLocation());
        currentUserDto.setDateOfBirth(user.getUserDetails().getDateOfBirth());
        return currentUserDto;
    }
}