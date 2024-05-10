package com.coders.rentkun.dtos.vehicles.converts;

import com.coders.rentkun.dtos.vehicles.responses.LogoResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleLogo;
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
import java.util.Random;

@Component
public class LogoDtoConverter {
    private final Path logoPath = Paths.get("C:\\Users\\isasa\\OneDrive\\Masaüstü\\rentkun\\src\\main\\resources\\images\\vehicle_images\\logos\\");

    @Value("${logo.upload.url}")
    private String uploadUrl;

    @Value("${logo.download.url}")
    private String downloadUrl;

    public LogoResponseDto convertToResponse(VehicleLogo entity) {
        return new LogoResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getUrl(),
                entity.getDownloadUrl()
        );
    }

    public VehicleLogo convertMultipartFileToEntity(MultipartFile file) throws IOException {
        Random random = new Random();
        String contentType = file.getContentType();
        assert contentType != null;
        String updatedContentType = contentType.substring(contentType.lastIndexOf('/') + 1);

        String changedFileName = "logo_" + random.nextInt() + "." + updatedContentType;
        Path folderPath = Paths.get(logoPath + "\\" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        String url = uploadUrl + changedFileName;
        String toDownload = downloadUrl + changedFileName;

        Path filePath = Paths.get(folderPath + "\\" + changedFileName);
        Files.write(filePath, file.getBytes());

        VehicleLogo logo = new VehicleLogo();
        logo.setName(changedFileName);
        logo.setType(updatedContentType);
        logo.setUrl(url);
        logo.setDownloadUrl(toDownload);
        logo.setFilePath(filePath.toString());
        logo.setCreatedAt(LocalDate.now());

        return logo;
    }

    public Resource convertEntityToResource(VehicleLogo foundLogo) {
        Path file = logoPath.resolve(foundLogo.getFilePath());
        try {
            return new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] convertResourceToByte (Resource resource) {
        try {
            InputStream inputStream = resource.getInputStream();
            return inputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("No file found");
        }
    }
}
