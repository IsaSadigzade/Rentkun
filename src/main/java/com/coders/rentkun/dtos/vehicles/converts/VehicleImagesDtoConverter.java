package com.coders.rentkun.dtos.vehicles.converts;

import com.coders.rentkun.dtos.vehicles.responses.VehicleImageResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleBrand;
import com.coders.rentkun.entities.vehicles.VehicleDetails;
import com.coders.rentkun.entities.vehicles.VehicleImages;
import com.coders.rentkun.entities.vehicles.VehicleModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class VehicleImagesDtoConverter {
    private final Path imagesPath = Paths.get("C:\\Users\\isasa\\OneDrive\\Masaüstü\\rentkun\\src\\main\\resources\\images\\vehicle_images\\vehicles\\");

    @Value("${vehicle.upload.url}")
    private String uploadUrl;

    @Value("${vehicle.download.url}")
    private String downloadUrl;

    public VehicleImageResponseDto convertToResponse(VehicleImages entity) {
        return new VehicleImageResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getUrl(),
                entity.getDownloadUrl()
        );
    }

    public VehicleImages convertToEntity(VehicleImageResponseDto dto) {
        return new VehicleImages(
                dto.getId(),
                dto.getName(),
                dto.getUrl(),
                dto.getDownloadUrl()
        );
    }

    public VehicleImages convertMultipartFileToEntity(MultipartFile file, Object[] objects, VehicleDetails details) throws IOException {
        VehicleBrand foundBrand = (VehicleBrand) objects[0];
        VehicleModel foundModel = (VehicleModel) objects[1];
        String contentType = file.getContentType();
        assert contentType != null;
        String updatedContentType = contentType.substring(contentType.lastIndexOf('/') + 1);

        String changedFileName = foundBrand + "_" + foundModel + "." + updatedContentType;
        Path folderPath = Paths.get(imagesPath + "\\" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        Path pathForEachVehicle = Paths.get(folderPath + "\\" + "vehicle_" + details.getId());

        if (!Files.exists(folderPath.resolve(changedFileName))) {
            Files.createDirectories(pathForEachVehicle);
        }

        String url = uploadUrl + changedFileName;
        String toDownload = downloadUrl + changedFileName;

        Path filePath = Paths.get(pathForEachVehicle + "\\" + changedFileName);
        Files.write(filePath, file.getBytes());

        VehicleImages images = new VehicleImages();
        images.setName(changedFileName);
        images.setType(updatedContentType);
        images.setUrl(url);
        images.setDownloadUrl(toDownload);
        images.setFilePath(filePath.toString());
        images.setCreatedAt(LocalDate.now());

        return images;
    }
}
