package com.coders.rentkun.dtos.vehicles.converts;

import com.coders.rentkun.dtos.vehicles.requests.CreateFuelTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateFuelTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.FuelTypeResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleFuelType;
import org.springframework.stereotype.Component;

@Component
public class FuelTypeDtoConverter {
    public FuelTypeResponseDto convertToResponse(VehicleFuelType entity) {
        return new FuelTypeResponseDto(entity.getId(), entity.getName());
    }

    public VehicleFuelType convertToEntity(CreateFuelTypeRequestDto fuelTypeRequestDto) {
        VehicleFuelType fuelType = new VehicleFuelType();
        fuelType.setName(fuelTypeRequestDto.getFuelTypeName());
        return fuelType;
    }

    public VehicleFuelType convertToEntity(VehicleFuelType foundFuelType, UpdateFuelTypeRequestDto fuelTypeRequestDto) {
        foundFuelType.setName(fuelTypeRequestDto.getFuelTypeName());
        return foundFuelType;
    }
}
