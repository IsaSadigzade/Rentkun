package com.coders.rentkun.services;

import com.coders.rentkun.core.utilities.results.*;
import com.coders.rentkun.dtos.users.converts.UserDtoConverter;
import com.coders.rentkun.dtos.users.requests.*;
import com.coders.rentkun.dtos.users.responses.CurrentUserResponseDto;
import com.coders.rentkun.dtos.users.responses.UpdatedEmailAndPhoneNumberResponseDto;
import com.coders.rentkun.entities.users.User;
import com.coders.rentkun.entities.users.UserInfos;
import com.coders.rentkun.repositories.UserRepository;
import com.coders.rentkun.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserInfosService userInfosService;
    private final UserDtoConverter userDtoConverter;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, UserInfosService userInfosService, UserDtoConverter userDtoConverter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userInfosService = userInfosService;
        this.userDtoConverter = userDtoConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByEmail(username);
    }

    @Transactional
    public void registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        if (!userRegisterRequestDto.getPassword().equals(userRegisterRequestDto.getConfirmPassword())) {
            new ErrorResult("Password and confirmation password don't matching.");
        }

        UserInfos savedUserInfos = userInfosService.save(userRegisterRequestDto.getUserDetails());
        UserInfos foundUserInfos = userInfosService.findByUserId(savedUserInfos.getId());

        userRepository.save(
                userDtoConverter.convertToEntity(userRegisterRequestDto, foundUserInfos)
        );

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRegisterRequestDto.getEmail(), userRegisterRequestDto.getPassword())
        );

        new SuccessDataResult<>("User registered successfully", jwtTokenProvider.generateToken(authentication));
    }

    public Authentication loginUser(UserLoginRequestDto userLoginRequestDto) {
        String email = userLoginRequestDto.getEmail();
        String password = passwordEncoder.encode(userLoginRequestDto.getPassword());

        User loggedUser = userRepository.findByEmail(email);

        if (loggedUser == null || !password.equals(loggedUser.getPassword())) {
            new ErrorResult("Incorrect email or password.");
        }

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }

    public DataResult<List<CurrentUserResponseDto>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<CurrentUserResponseDto> foundUsers = new ArrayList<>();
        for (User user : users) {
            CurrentUserResponseDto currentUserResponseDto = userDtoConverter.convertToResponse(user);
//            currentUserResponseDto.setImage(downloadImageFromFileSystem(user.getUserImage().getId()));
            foundUsers.add(currentUserResponseDto);
        }
        return new SuccessDataResult<>(foundUsers, "Data listed successfully.");
    }

    public Result updatePassword(Long userId, UserPasswordUpdateRequestDto userPasswordUpdateRequestDto) {
        User foundUser = findUserById(userId);
        String oldPassword = passwordEncoder.encode(userPasswordUpdateRequestDto.getOldPassword());
        String newPassword = passwordEncoder.encode(userPasswordUpdateRequestDto.getNewPassword());

        if (!oldPassword.equals(foundUser.getPassword())) {
            return new ErrorResult("Old password does not match.");
        }

        if (foundUser.getPassword().equals(newPassword)) {
            return new ErrorResult("New password and old password cannot be the same.");
        }

        foundUser.setPassword(newPassword);
        userRepository.save(foundUser);
        return new SuccessResult("User password updated successfully.");
    }

    public DataResult<CurrentUserResponseDto> getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            CurrentUserResponseDto currentUserResponseDto = userDtoConverter.convertToResponse(user);
//            currentUserResponseDto.setImage(downloadImageFromFileSystem(user.getUserImage().getId()));
            return new SuccessDataResult<>(currentUserResponseDto, "User with id = " + id + " is present");
        }
        return new ErrorDataResult<>("User not found");
    }

    @Transactional
    public CurrentUserResponseDto updateUser(Long userId, UserDetailsRequestDto userDetailsRequestDto) {
        User foundUser = findUserById(userId);
        userInfosService.updateUserInfos(foundUser.getUserInfos().getId(), userDetailsRequestDto);
        UserInfos foundUserInfos = userInfosService.findByUserId(foundUser.getUserInfos().getId());

        return userDtoConverter.convertToResponse(
                userRepository.save(
                        userDtoConverter.convertToEntity(
                                foundUser,
                                foundUserInfos
                        )
                )
        );
    }

    public void deleteUser(Long userId) {
        if (isUserExistById(userId)) {
            new ErrorResult("User not found.");
        }
        userInfosService.deleteUserInfos(findUserById(userId).getUserInfos().getId());
        userRepository.deleteById(userId);
        new SuccessResult("User deleted successfully");
    }

    public UpdatedEmailAndPhoneNumberResponseDto updateEmailAndPhoneNumber(Long userId, UserEmailAndPhoneNumberUpdateRequestDto userEmailAndPhoneNumberUpdateRequestDto) {
        User foundUser = findUserById(userId);
        userInfosService.updatePhoneNumber(foundUser.getUserInfos().getId(), userEmailAndPhoneNumberUpdateRequestDto);
        foundUser.setEmail(userEmailAndPhoneNumberUpdateRequestDto.getEmail());
        return userDtoConverter.convertToEmailAndPhoneNumberResponse(foundUser);
    }

    protected User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    protected User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    protected boolean isUserExistById(Long userId) {
        return userRepository.existsById(userId);
    }

//    public String uploadImageToFileSystem(Long userId, MultipartFile file) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            String fileName = file.getOriginalFilename();
//            assert fileName != null;
//            String newFileName = fileName.replaceAll(fileName, user.getUserInfos().getFirstName() + "_" + user.getUserInfos().getLastName()) + ".png";
//            String FOLDER_PATH = "C:\\Users\\isasa\\OneDrive\\Masaüstü\\rentkun\\src\\main\\resources\\images\\user_profile_photos\\";
//            String folderPath = FOLDER_PATH + "\\" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//            try {
//                Path directoryPath = Paths.get(folderPath);
//                if (!Files.exists(directoryPath)) {
//                    Files.createDirectories(directoryPath);
//                }
//
//                Path filePath = Paths.get(directoryPath + "\\" + newFileName);
//                Files.write(filePath, file.getBytes());
//
//                UserImage userImage = user.getUserImage();
//                if (userImage == null) {
//                    userImage = new UserImage();
//                    userImage.setCreatedAt(LocalDate.now());
//                }
//                userImage.setName(newFileName);
//                userImage.setType(file.getContentType());
//                userImage.setFilePath(filePath.toString());
//                userImage.setCreatedAt(userImage.getCreatedAt());
//                userImage.setUpdatedAt(LocalDate.now());
//                userImage.setUser(user);
//
//                userImageRepository.save(userImage);
//
//                user.setUserImage(userImage);
//                userRepository.save(user);
//
//            } catch (IOException e) {
//                throw new RuntimeException("Error uploading file", e);
//            }
//
//            return "File uploaded successfully : " + folderPath + newFileName;
//        } else {
//            throw new RuntimeException("User not found with id: " + userId);
//        }
//    }
//
//
//    public byte[] downloadImageFromFileSystem(Long userId) {
//        Optional<User> foundUser = userRepository.findById(userId);
//        if (foundUser.isEmpty()) {
//            throw new RuntimeException("User not found with id: " + userId);
//        }
//        String filePath = foundUser.get().getUserImage().getFilePath();
//        byte[] images;
//        try {
//            images = Files.readAllBytes(new File(filePath).toPath());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return images;
//    }
}