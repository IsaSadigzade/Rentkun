package com.coders.rentkun.services.impl;

import com.coders.rentkun.dtos.vehicles.converts.LogoDtoConverter;
import com.coders.rentkun.dtos.vehicles.responses.VehicleLogoResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleLogo;
import com.coders.rentkun.repositories.VehicleLogoRepository;
import com.coders.rentkun.services.VehicleLogoService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class VehicleLogoServiceImpl implements VehicleLogoService {
    private final VehicleLogoRepository vehicleLogoRepository;
    private final LogoDtoConverter logoDtoConverter;
    private final Path root = Paths.get("C:\\Users\\isasa\\OneDrive\\Masaüstü\\rentkun\\src\\main\\resources\\images\\vehicle_images\\logos\\");

    public VehicleLogoServiceImpl(VehicleLogoRepository vehicleLogoRepository, LogoDtoConverter logoDtoConverter) {
        this.vehicleLogoRepository = vehicleLogoRepository;
        this.logoDtoConverter = logoDtoConverter;
    }

    @Override
    public VehicleLogoResponseDto save(MultipartFile file) {
        try {
            return logoDtoConverter.convertToResponse(
                    vehicleLogoRepository.save(
                            logoDtoConverter.convertMultipartFileToEntity(file)
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getLogoByFileName(String filename) {
        return logoDtoConverter.convertResourceToByte(
                logoDtoConverter.convertEntityToResource(findByVehicleLogoName(filename))
        );
    }


    @Override
    public Resource download(String filename) {
        Resource foundResource = logoDtoConverter.convertEntityToResource(findByVehicleLogoName(filename));

        if (foundResource.exists() || foundResource.isReadable()) {
            return foundResource;
        } else {
            throw new RuntimeException("Could not read the file!");
        }
    }

//    @Override
//    public void deleteAll() {
//        FileSystemUtils.deleteRecursively(root.toFile());
//    }
//
//    @Override
//    public Stream<Path> loadAll() {
//        try {
//            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
//        } catch (IOException e) {
//            throw new RuntimeException("Could not load the files!");
//        }
//    }

    private VehicleLogo findByVehicleLogoName(String vehicleLogoName) {
        return vehicleLogoRepository.findByName(vehicleLogoName)
                .orElseThrow(() -> new RuntimeException("Logo doesn't found with name: " + vehicleLogoName));
    }
}
