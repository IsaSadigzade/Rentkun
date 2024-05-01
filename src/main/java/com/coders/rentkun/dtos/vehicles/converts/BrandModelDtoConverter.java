package com.coders.rentkun.dtos.vehicles.converts;

import com.coders.rentkun.dtos.vehicles.requests.CreateModelRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateModelRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.BrandModelResponseDto;
import com.coders.rentkun.dtos.vehicles.responses.ModelResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleBrand;
import com.coders.rentkun.entities.vehicles.VehicleModel;
import org.springframework.stereotype.Component;

@Component
public class BrandModelDtoConverter {
    public BrandModelResponseDto convertToBrandModelResponse(VehicleModel entity) {
        BrandModelResponseDto responseDto = new BrandModelResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setBrandName(entity.getVehicleBrand().getName());
        responseDto.setModelName(entity.getName());
        return responseDto;
    }

    public ModelResponseDto convertToModelResponse(VehicleModel entity) {
        ModelResponseDto responseDto = new ModelResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setModelName(entity.getName());
        return responseDto;
    }

    public VehicleModel convertFromRequestToEntity(VehicleBrand brandEntity, CreateModelRequestDto modelRequestDto) {
        VehicleModel modelEntity = new VehicleModel();
        modelEntity.setName(modelRequestDto.getModelName());
        modelEntity.setVehicleBrand(brandEntity);
        return modelEntity;
    }


    public VehicleModel convertFromRequestToEntity(VehicleModel foundModel, UpdateModelRequestDto modelRequestDto) {
        foundModel.setName(modelRequestDto.getBrandName());
        return foundModel;
    }
}
