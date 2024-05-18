package com.coders.rentkun.services;

import com.coders.rentkun.dtos.users.converts.UserImageDtoConverter;
import com.coders.rentkun.dtos.users.responses.UserImagesResponseDto;
import com.coders.rentkun.entities.users.UserImage;
import com.coders.rentkun.entities.users.UserInfos;
import com.coders.rentkun.repositories.UserImageRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserImageService {
    private final UserImageRepository userImageRepository;
    private final UserImageDtoConverter userImageDtoConverter;

    public UserImageService(UserImageRepository userImageRepository, UserImageDtoConverter userImageDtoConverter) {
        this.userImageRepository = userImageRepository;
        this.userImageDtoConverter = userImageDtoConverter;
    }

    public UserImagesResponseDto save(MultipartFile file, UserInfos userInfos) {
        try {
            return userImageDtoConverter.convertToResponse(
                    userImageRepository.save(
                            userImageDtoConverter.convertMultipartFileToEntity(file, userInfos)
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Resource download(String filename) {
        Resource foundResource = userImageDtoConverter.convertEntityToResource(findByUserImageName(filename));

        if (foundResource.exists() || foundResource.isReadable()) {
            return foundResource;
        } else {
            throw new RuntimeException("Could not read the file!");
        }
    }

    protected UserImage findByUserImageId(Long userImageId) {
        return userImageRepository.findById(userImageId)
                .orElseThrow(() -> new RuntimeException("User Image doesn't found with id: " + userImageId));
    }

    protected UserImage findByUserImageName(String userImageName) {
        return userImageRepository.findByName(userImageName)
                .orElseThrow(() -> new RuntimeException("User Image doesn't found with name: " + userImageName));
    }

    protected boolean isFileExist(Long userImageId) {
        return userImageRepository.existsById(userImageId);
    }
}
