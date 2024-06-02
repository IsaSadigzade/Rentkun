package com.coders.rentkun.services.vehicles;

import com.coders.rentkun.dtos.vehicles.converts.VehicleTypeDtoConverter;
import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateVehicleTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.VehicleTypeResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleType;
import com.coders.rentkun.exception.VehicleTypeDoesNotExistException;
import com.coders.rentkun.exception.VehicleTypeNotFoundException;
import com.coders.rentkun.repositories.vehicles.VehicleTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleTypeService {
    private final VehicleTypeRepository vehicleTypeRepository;
    private final VehicleTypeDtoConverter vehicleTypeDtoConverter;

    public VehicleTypeService(VehicleTypeRepository vehicleTypeRepository, VehicleTypeDtoConverter vehicleTypeDtoConverter) {
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.vehicleTypeDtoConverter = vehicleTypeDtoConverter;
    }

    public VehicleTypeResponseDto saveVehicleType(CreateVehicleTypeRequestDto vehicleTypeRequestDto) {
        return vehicleTypeDtoConverter.convertToResponse(
                vehicleTypeRepository.save(
                        vehicleTypeDtoConverter.convertToEntity(vehicleTypeRequestDto)
                )
        );
    }

    public List<VehicleTypeResponseDto> getAllVehicleTypes() {
        return vehicleTypeRepository.findAll().stream()
                .map(vehicleTypeDtoConverter::convertToResponse)
                .collect(Collectors.toList());
    }

    public VehicleTypeResponseDto getVehicleTypeByVehicleTypeId(Long vehicleTypeId) {
        return vehicleTypeDtoConverter.convertToResponse(findByVehicleTypeId(vehicleTypeId));
    }

    public VehicleTypeResponseDto getVehicleTypeByVehicleTypeName(String vehicleTypeName) {
        return vehicleTypeDtoConverter.convertToResponse(findByVehicleTypeName(vehicleTypeName));
    }

    public VehicleTypeResponseDto updateVehicleType(Long vehicleTypeId, UpdateVehicleTypeRequestDto vehicleTypeRequestDto) {
        return vehicleTypeDtoConverter.convertToResponse(
                vehicleTypeRepository.save(
                        vehicleTypeDtoConverter.convertToEntity(findByVehicleTypeId(vehicleTypeId), vehicleTypeRequestDto)
                )
        );
    }

    public void deleteVehicleType(Long vehicleTypeId) {
        if (isVehicleTypeExist(vehicleTypeId)) {
            vehicleTypeRepository.deleteById(vehicleTypeId);
        } else {
            throw new VehicleTypeDoesNotExistException("VehicleType doesn't exist by following vehicleTypeId: " + vehicleTypeId);
        }
    }

    protected VehicleType findByVehicleTypeId(Long vehicleTypeId) {
        return vehicleTypeRepository.findById(vehicleTypeId)
                .orElseThrow(() -> new VehicleTypeNotFoundException("Vehicle FuelType couldn't be found by following id: " + vehicleTypeId));
    }

    protected VehicleType findByVehicleTypeName(String vehicleTypeName) {
        return vehicleTypeRepository.findByName(vehicleTypeName)
                .orElseThrow(() -> new VehicleTypeNotFoundException("Vehicle FuelType couldn't be found by following featureName: " + vehicleTypeName));
    }

    protected boolean isVehicleTypeExist(Long vehicleTypeId) {
        return vehicleTypeRepository.existsById(vehicleTypeId);
    }
}
