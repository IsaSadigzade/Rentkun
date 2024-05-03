package com.coders.rentkun.services.impl;

import com.coders.rentkun.dtos.vehicles.converts.GearboxTypeDtoConverter;
import com.coders.rentkun.dtos.vehicles.requests.CreateGearboxTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateGearboxTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.GearboxTypeResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleGearboxType;
import com.coders.rentkun.exception.GearboxTypeDoesNotExistException;
import com.coders.rentkun.exception.GearboxTypeNotFoundException;
import com.coders.rentkun.repositories.VehicleGearboxTypeRepository;
import com.coders.rentkun.services.VehicleGearboxTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleGearboxTypeServiceImpl implements VehicleGearboxTypeService {
    private final VehicleGearboxTypeRepository vehicleGearboxTypeRepository;
    private final GearboxTypeDtoConverter gearboxTypeDtoConverter;

    public VehicleGearboxTypeServiceImpl(VehicleGearboxTypeRepository vehicleGearboxTypeRepository, GearboxTypeDtoConverter gearboxTypeDtoConverter) {
        this.vehicleGearboxTypeRepository = vehicleGearboxTypeRepository;
        this.gearboxTypeDtoConverter = gearboxTypeDtoConverter;
    }

    @Override
    public GearboxTypeResponseDto saveGearboxType(CreateGearboxTypeRequestDto gearboxTypeRequestDto) {
        return gearboxTypeDtoConverter.convertToResponse(
                vehicleGearboxTypeRepository.save(
                        gearboxTypeDtoConverter.convertToEntity(gearboxTypeRequestDto)
                )
        );
    }

    @Override
    public List<GearboxTypeResponseDto> getAllGearboxTypes() {
        return vehicleGearboxTypeRepository.findAll().stream()
                .map(gearboxTypeDtoConverter::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GearboxTypeResponseDto getGearboxTypeByGearboxTypeId(Long gearboxTypeId) {
        return gearboxTypeDtoConverter.convertToResponse(findByGearboxTypeId(gearboxTypeId));
    }

    @Override
    public GearboxTypeResponseDto getGearboxTypeByGearboxTypeName(String gearboxTypeName) {
        return gearboxTypeDtoConverter.convertToResponse(findByGearboxTypeName(gearboxTypeName));
    }

    @Override
    public GearboxTypeResponseDto updateGearboxType(Long gearboxTypeId, UpdateGearboxTypeRequestDto gearboxTypeRequestDto) {
        VehicleGearboxType foundGearboxType = findByGearboxTypeId(gearboxTypeId);
        return gearboxTypeDtoConverter.convertToResponse(
                vehicleGearboxTypeRepository.save(
                        gearboxTypeDtoConverter.convertToEntity(foundGearboxType, gearboxTypeRequestDto)
                )
        );
    }

    @Override
    public void deleteGearboxType(Long gearboxTypeId) {
        if (isGearboxTypeExist(gearboxTypeId)) {
            vehicleGearboxTypeRepository.deleteById(gearboxTypeId);
        } else {
            throw new GearboxTypeDoesNotExistException("GearboxType doesn't exist by following gearboxTypeId: " + gearboxTypeId);
        }
    }

    public VehicleGearboxType findByGearboxTypeId(Long gearboxTypeId) {
        return vehicleGearboxTypeRepository.findById(gearboxTypeId)
                .orElseThrow(() -> new GearboxTypeNotFoundException("Vehicle GearboxType couldn't be found by following id: " + gearboxTypeId));
    }

    public VehicleGearboxType findByGearboxTypeName(String gearboxTypeName) {
        return vehicleGearboxTypeRepository.findByName(gearboxTypeName)
                .orElseThrow(() -> new GearboxTypeNotFoundException("Vehicle GearboxType couldn't be found by following gearboxTypeName: " + gearboxTypeName));
    }

    private boolean isGearboxTypeExist(Long gearboxTypeId) {
        return vehicleGearboxTypeRepository.existsById(gearboxTypeId);
    }
}
