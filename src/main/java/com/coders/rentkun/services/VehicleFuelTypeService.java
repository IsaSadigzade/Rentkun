package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.requests.CreateFuelTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateFuelTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.FuelTypeResponseDto;

import java.util.List;

public interface VehicleFuelTypeService {
    FuelTypeResponseDto saveFuelType(CreateFuelTypeRequestDto fuelTypeRequestDto);

    List<FuelTypeResponseDto> getAllFuelTypes();

    FuelTypeResponseDto getFuelTypeByFuelTypeId(Long fuelTypeId);

    FuelTypeResponseDto getFuelTypeByFuelTypeName(String fuelTypeName);

    FuelTypeResponseDto updateFuelType(Long fuelTypeId, UpdateFuelTypeRequestDto fuelTypeRequestDto);

    void deleteFuelType(Long fuelTypeId);
}
