package com.coders.rentkun.dtos.vehicles.converts;

import com.coders.rentkun.dtos.vehicles.requests.CreateGearboxTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateGearboxTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.GearboxTypeResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleGearboxType;
import org.springframework.stereotype.Component;

@Component
public class GearboxTypeDtoConverter {
    public GearboxTypeResponseDto convertToResponse(VehicleGearboxType entity) {
        return new GearboxTypeResponseDto(entity.getId(), entity.getName());
    }

    public VehicleGearboxType convertToEntity(CreateGearboxTypeRequestDto gearboxTypeRequestDto) {
        VehicleGearboxType gearboxType = new VehicleGearboxType();
        gearboxType.setName(gearboxTypeRequestDto.getGearboxName());
        return gearboxType;
    }

    public VehicleGearboxType convertToEntity(VehicleGearboxType foundGearboxType, UpdateGearboxTypeRequestDto gearboxTypeRequestDto) {
        foundGearboxType.setName(gearboxTypeRequestDto.getGearboxName());
        return foundGearboxType;
    }
}
