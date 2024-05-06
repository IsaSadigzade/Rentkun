package com.coders.rentkun.services.impl;

import com.coders.rentkun.dtos.vehicles.converts.LogoDtoConverter;
import com.coders.rentkun.dtos.vehicles.responses.LogoResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleLogo;
import com.coders.rentkun.exception.LogoDoesNotExistException;
import com.coders.rentkun.exception.LogoNotFoundException;
import com.coders.rentkun.repositories.VehicleLogoRepository;
import com.coders.rentkun.services.VehicleLogoService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleLogoServiceImpl implements VehicleLogoService {
    private final VehicleLogoRepository vehicleLogoRepository;
    private final LogoDtoConverter logoDtoConverter;

    public VehicleLogoServiceImpl(VehicleLogoRepository vehicleLogoRepository, LogoDtoConverter logoDtoConverter) {
        this.vehicleLogoRepository = vehicleLogoRepository;
        this.logoDtoConverter = logoDtoConverter;
    }

    @Override
    public LogoResponseDto save(MultipartFile file) {
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
    public List<LogoResponseDto> getLogos() {
        return vehicleLogoRepository.findAll().stream()
                .map(logoDtoConverter::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LogoResponseDto getLogoResponseById(Long vehicleLogoId) {
        return logoDtoConverter.convertToResponse(
                findByVehicleLogoId(vehicleLogoId)
        );
    }

    @Override
    public byte[] getLogoById(Long vehicleLogoId) {
        return getLogoByFileName(findByVehicleLogoId(vehicleLogoId).getName());
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

    @Override
    public void deleteVehicleLogo(Long vehicleLogoId) {
        if (isFileExist(vehicleLogoId)) {
            vehicleLogoRepository.deleteById(vehicleLogoId);
        } else {
            throw new LogoDoesNotExistException("Vehicle Logo does not exist!");
        }
    }

    private VehicleLogo findByVehicleLogoId(Long vehicleLogoId) {
        return vehicleLogoRepository.findById(vehicleLogoId)
                .orElseThrow(() -> new LogoNotFoundException("Logo doesn't found with id: " + vehicleLogoId));
    }

    private VehicleLogo findByVehicleLogoName(String vehicleLogoName) {
        return vehicleLogoRepository.findByName(vehicleLogoName)
                .orElseThrow(() -> new LogoNotFoundException("Logo doesn't found with name: " + vehicleLogoName));
    }

    private boolean isFileExist(Long vehicleLogoId) {
        return vehicleLogoRepository.existsById(vehicleLogoId);
    }
}
