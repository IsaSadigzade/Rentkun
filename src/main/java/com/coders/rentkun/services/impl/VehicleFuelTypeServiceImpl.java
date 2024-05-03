package com.coders.rentkun.services.impl;

import com.coders.rentkun.dtos.vehicles.converts.FuelTypeDtoConverter;
import com.coders.rentkun.dtos.vehicles.requests.CreateFuelTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateFuelTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.FuelTypeResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleFuelType;
import com.coders.rentkun.exception.FeatureDoesNotExistException;
import com.coders.rentkun.exception.FuelTypeNotFoundException;
import com.coders.rentkun.repositories.VehicleFuelTypeRepository;
import com.coders.rentkun.services.VehicleFuelTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleFuelTypeServiceImpl implements VehicleFuelTypeService {
    private final VehicleFuelTypeRepository vehicleFuelTypeRepository;
    private final FuelTypeDtoConverter fuelTypeDtoConverter;

    public VehicleFuelTypeServiceImpl(VehicleFuelTypeRepository vehicleFuelTypeRepository, FuelTypeDtoConverter fuelTypeDtoConverter) {
        this.vehicleFuelTypeRepository = vehicleFuelTypeRepository;
        this.fuelTypeDtoConverter = fuelTypeDtoConverter;
    }

    @Override
    public FuelTypeResponseDto saveFuelType(CreateFuelTypeRequestDto fuelTypeRequestDto) {
        return fuelTypeDtoConverter.convertToResponse(
                vehicleFuelTypeRepository.save(
                        fuelTypeDtoConverter.convertToEntity(fuelTypeRequestDto)
                )
        );
    }

    @Override
    public List<FuelTypeResponseDto> getAllFuelTypes() {
        return vehicleFuelTypeRepository.findAll().stream()
                .map(fuelTypeDtoConverter::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FuelTypeResponseDto getFuelTypeByFuelTypeId(Long fuelTypeId) {
        return fuelTypeDtoConverter.convertToResponse(findByFuelTypeId(fuelTypeId));
    }

    @Override
    public FuelTypeResponseDto getFuelTypeByFuelTypeName(String fuelTypeName) {
        return fuelTypeDtoConverter.convertToResponse(findByFuelTypeName(fuelTypeName));
    }

    @Override
    public FuelTypeResponseDto updateFuelType(Long fuelTypeId, UpdateFuelTypeRequestDto fuelTypeRequestDto) {
        VehicleFuelType foundFuelType = findByFuelTypeId(fuelTypeId);
        return fuelTypeDtoConverter.convertToResponse(
                vehicleFuelTypeRepository.save(
                        fuelTypeDtoConverter.convertToEntity(foundFuelType, fuelTypeRequestDto)
                )
        );
    }

    @Override
    public void deleteFuelType(Long fuelTypeId) {
        if(isFuelTypeExist(fuelTypeId)) {
            vehicleFuelTypeRepository.deleteById(fuelTypeId);
        } else {
            throw new FeatureDoesNotExistException("FuelType doesn't exist by following fuelTypeId: " + fuelTypeId);
        }
    }

    public VehicleFuelType findByFuelTypeId(Long fuelTypeId) {
        return vehicleFuelTypeRepository.findById(fuelTypeId)
                .orElseThrow(() -> new FuelTypeNotFoundException("Vehicle FuelType couldn't be found by following id: " + fuelTypeId));
    }

    public VehicleFuelType findByFuelTypeName(String fuelTypeName) {
        return vehicleFuelTypeRepository.findByName(fuelTypeName)
                .orElseThrow(() -> new FuelTypeNotFoundException("Vehicle FuelType couldn't be found by following featureName: " + fuelTypeName));
    }

    private boolean isFuelTypeExist(Long fuelTypeId) {
        return vehicleFuelTypeRepository.existsById(fuelTypeId);
    }
}
