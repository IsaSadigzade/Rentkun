package com.coders.rentkun.dtos.users.converts;

import com.coders.rentkun.dtos.users.responses.UserImagesResponseDto;
import com.coders.rentkun.entities.users.UserImage;
import com.coders.rentkun.entities.users.UserInfos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UserImageDtoConverter {
    private final Path imagePath = Paths.get("C:\\Users\\isasa\\OneDrive\\Masaüstü\\rentkun\\src\\main\\resources\\images\\user_profile_photos\\");

    @Value("${user.upload.url}")
    private String uploadUrl;

    @Value("${user.download.url}")
    private String downloadUrl;

    public UserImagesResponseDto convertToResponse(UserImage entity) {
        return new UserImagesResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getUrl(),
                entity.getDownloadUrl()
        );
    }

    public UserImage convertToEntity(UserImagesResponseDto dto) {
        return new UserImage(
                dto.getId(),
                dto.getName(),
                dto.getUrl(),
                dto.getDownloadUrl()
        );
    }

    public UserImage convertMultipartFileToEntity(MultipartFile file, UserInfos foundUserInfos) throws IOException {
        String contentType = file.getContentType();
        assert contentType != null;
        String updatedContentType = contentType.substring(contentType.lastIndexOf('/') + 1);

        String changedFileName = foundUserInfos.getFirstName() + "_" + foundUserInfos.getLastName() + "." + updatedContentType;
        Path folderPath = Paths.get(imagePath + "\\" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        Path pathForEachVehicle = Paths.get(folderPath + "\\" + "vehicle_" + foundUserInfos.getId());

        if (!Files.exists(folderPath.resolve(changedFileName))) {
            Files.createDirectories(pathForEachVehicle);
        }

        String url = uploadUrl + changedFileName;
        String toDownload = downloadUrl + changedFileName;

        Path filePath = Paths.get(pathForEachVehicle + "\\" + changedFileName);
        Files.write(filePath, file.getBytes());

        UserImage image = new UserImage();
        image.setName(changedFileName);
        image.setType(updatedContentType);
        image.setUrl(url);
        image.setDownloadUrl(toDownload);
        image.setFilePath(filePath.toString());
        image.setCreatedAt(LocalDate.now());

        return image;
    }

    public Resource convertEntityToResource(UserImage foundUserImage) {
        Path file = imagePath.resolve(foundUserImage.getFilePath());
        try {
            return new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] convertResourceToByte(Resource resource) {
        try {
            InputStream inputStream = resource.getInputStream();
            return inputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("No file found");
        }
    }
}
