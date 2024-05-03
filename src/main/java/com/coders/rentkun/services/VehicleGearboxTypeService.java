package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.requests.CreateGearboxTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateGearboxTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.GearboxTypeResponseDto;

import java.util.List;

public interface VehicleGearboxTypeService {
    GearboxTypeResponseDto saveGearboxType(CreateGearboxTypeRequestDto gearboxTypeRequestDto);

    List<GearboxTypeResponseDto> getAllGearboxTypes();

    GearboxTypeResponseDto getGearboxTypeByGearboxTypeId(Long gearboxTypeId);

    GearboxTypeResponseDto getGearboxTypeByGearboxTypeName(String gearboxTypeName);

    GearboxTypeResponseDto updateGearboxType(Long gearboxTypeId, UpdateGearboxTypeRequestDto gearboxTypeRequestDto);

    void deleteGearboxType(Long gearboxTypeId);
}
