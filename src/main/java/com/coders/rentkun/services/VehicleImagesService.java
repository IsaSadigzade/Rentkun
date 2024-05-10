package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.converts.VehicleImagesDtoConverter;
import com.coders.rentkun.dtos.vehicles.responses.VehicleImageResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleDetails;
import com.coders.rentkun.entities.vehicles.VehicleImages;
import com.coders.rentkun.repositories.VehicleImagesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VehicleImagesService {
    private final VehicleImagesRepository vehicleImagesRepository;
    private final VehicleImagesDtoConverter vehicleImageDtoConverter;

    public VehicleImagesService(VehicleImagesRepository vehicleImagesRepository, VehicleImagesDtoConverter vehicleImageDtoConverter) {
        this.vehicleImagesRepository = vehicleImagesRepository;
        this.vehicleImageDtoConverter = vehicleImageDtoConverter;
    }

    public VehicleImageResponseDto save(MultipartFile file, Object[] objects, VehicleDetails details) {
        try {
            return vehicleImageDtoConverter.convertToResponse(
                    vehicleImagesRepository.save(
                            vehicleImageDtoConverter.convertMultipartFileToEntity(file, objects, details)
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected VehicleImages findById(Long id) {
        return vehicleImagesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle images not found with id: " + id));
    }
}
