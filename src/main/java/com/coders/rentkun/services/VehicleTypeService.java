package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateVehicleTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.VehicleTypeResponseDto;

import java.util.List;

public interface VehicleTypeService {
    VehicleTypeResponseDto saveVehicleType(CreateVehicleTypeRequestDto vehicleTypeRequestDto);

    List<VehicleTypeResponseDto> getAllVehicleTypes();

    VehicleTypeResponseDto getVehicleTypeByVehicleTypeId(Long vehicleTypeId);

    VehicleTypeResponseDto getVehicleTypeByVehicleTypeName(String vehicleTypeName);

    VehicleTypeResponseDto updateVehicleType(Long vehicleTypeId, UpdateVehicleTypeRequestDto vehicleTypeRequestDto);

    void deleteVehicleType(Long vehicleTypeId);
}
