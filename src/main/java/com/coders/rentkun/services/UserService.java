package com.coders.rentkun.services;

import com.coders.rentkun.core.utilities.results.*;
import com.coders.rentkun.dtos.users.converts.UserDtoConverter;
import com.coders.rentkun.dtos.users.requests.*;
import com.coders.rentkun.dtos.users.responses.CurrentUserResponseDto;
import com.coders.rentkun.entities.users.User;
import com.coders.rentkun.entities.users.UserImage;
import com.coders.rentkun.entities.users.UserInfos;
import com.coders.rentkun.repositories.UserImageRepository;
import com.coders.rentkun.repositories.UserInfosRepository;
import com.coders.rentkun.repositories.UserRepository;
import com.coders.rentkun.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserService{

    private final UserRepository userRepository;
    private final UserInfosRepository userInfosRepository;
    private final UserImageRepository userImageRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserInfosService userInfosService;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserInfosRepository userInfosRepository, UserImageRepository userImageRepository, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, UserInfosService userInfosService, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userInfosRepository = userInfosRepository;
        this.userImageRepository = userImageRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userInfosService = userInfosService;
        this.userDtoConverter = userDtoConverter;
    }

//    @Override
//    public User loadUserByUsername(String username) throws UsernameNotFoundException {
//        return findUserByEmail(username);
//    }

    @Transactional
    public DataResult<String> registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        if (!userRegisterRequestDto.getPassword().equals(userRegisterRequestDto.getConfirmPassword())) {
            return new ErrorDataResult<>("Password and confirmation password don't matching.");
        }

        UserInfos savedUserInfos = userInfosService.save(userRegisterRequestDto.getUserDetails());
        UserInfos foundUserInfos = userInfosService.findByUserId(savedUserInfos.getId());

        userRepository.save(
                userDtoConverter.convertToEntity(userRegisterRequestDto, foundUserInfos)
        );

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRegisterRequestDto.getEmail(), userRegisterRequestDto.getPassword())
        );

        jwtTokenProvider.generateToken(authentication);

        return new SuccessDataResult<>("User registered successfully");
    }

    public Result loginUser(UserLoginRequestDto userLoginRequestDto) {
        String email = userLoginRequestDto.getEmail();
        String password = userLoginRequestDto.getPassword();

        User loggedUser = userRepository.findByEmail(email);

        if (loggedUser == null || !password.equals(loggedUser.getPassword())) {
            return new ErrorResult("Incorrect email or password.");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        jwtTokenProvider.generateToken(authentication);

        return new SuccessResult("User logged in successfully.");
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
            Optional<UserInfos> optionalUserDetails = userInfosRepository.findById(user.getUserInfos().getId());
//            Optional<UserImage> optionalUserImage = userImageRepository.findByFeatureId(user.getUserImage().getId());
            if (optionalUserDetails.isPresent()) {
                UserInfos userInfos = optionalUserDetails.get();
                userInfos.setFirstName(userUpdateRequestDto.getFirstName());
                userInfos.setLastName(userUpdateRequestDto.getLastName());
//                userInfos.setEmail(userUpdateRequestDto.getEmail());
                userInfos.setPhoneNumber(userUpdateRequestDto.getPhoneNumber());
                userInfos.setGender(userUpdateRequestDto.getGender());
                userInfos.setCityAndZipCode(userUpdateRequestDto.getCityAndZipCode());
                userInfos.setLocation(userUpdateRequestDto.getLocation());
                userInfos.setDateOfBirth(userUpdateRequestDto.getBirthDate());
//                userInfos.setUser(user);
                userInfosRepository.save(userInfos);
            } else {
                return new ErrorResult("UserInfos not found");
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
            Optional<UserInfos> optionalUserDetails = userInfosRepository.findById(user.getUserInfos().getId());
            if (optionalUserDetails.isPresent()) {
                UserInfos userInfos = optionalUserDetails.get();
//                userInfos.setEmail(userEmailAndPhoneNumberUpdateRequestDto.getEmail());
                userInfos.setPhoneNumber(userEmailAndPhoneNumberUpdateRequestDto.getPhoneNumber());
                userInfosRepository.save(userInfos);
            } else {
                return new ErrorResult("UserInfos not found");
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
            String newFileName = fileName.replaceAll(fileName, user.getUserInfos().getFirstName() + "_" + user.getUserInfos().getLastName()) + ".png";
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

    protected User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    protected User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private static CurrentUserResponseDto entityToResponse(User user) {
        CurrentUserResponseDto currentUserDto = new CurrentUserResponseDto();
        currentUserDto.setId(user.getId());
        currentUserDto.setEmail(user.getEmail());
        currentUserDto.setFirstName(user.getUserInfos().getFirstName());
        currentUserDto.setLastName(user.getUserInfos().getLastName());
        currentUserDto.setPhoneNumber(user.getUserInfos().getPhoneNumber());
        currentUserDto.setGender(user.getUserInfos().getGender());
        currentUserDto.setCityAndZipCode(user.getUserInfos().getCityAndZipCode());
        currentUserDto.setLocation(user.getUserInfos().getLocation());
        currentUserDto.setDateOfBirth(user.getUserInfos().getDateOfBirth());
        return currentUserDto;
    }
}