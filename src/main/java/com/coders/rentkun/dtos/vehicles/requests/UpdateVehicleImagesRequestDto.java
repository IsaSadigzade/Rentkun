package com.coders.rentkun.dtos.vehicles.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehicleImagesRequestDto {
    private String name;
    private String type;
    private Set<MultipartFile> files;
}
