package com.coders.rentkun.dtos.vehicles.converts;

import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateVehicleTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.VehicleTypeResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleType;
import org.springframework.stereotype.Component;

@Component
public class VehicleTypeDtoConverter {
    public VehicleTypeResponseDto convertToResponse(VehicleType entity) {
        return new VehicleTypeResponseDto(entity.getId(), entity.getName());
    }

    public VehicleType convertToEntity(CreateVehicleTypeRequestDto vehicleTypeRequestDto) {
        VehicleType vehicleType = new VehicleType();
        vehicleType.setName(vehicleTypeRequestDto.getVehicleTypeName());
        return vehicleType;
    }

    public VehicleType convertToEntity(VehicleType foundVehicleType, UpdateVehicleTypeRequestDto vehicleTypeRequestDto) {
        foundVehicleType.setName(vehicleTypeRequestDto.getVehicleTypeName());
        return foundVehicleType;
    }
}
