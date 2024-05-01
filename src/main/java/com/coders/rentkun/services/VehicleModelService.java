package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.requests.CreateModelRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateModelRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.BrandModelResponseDto;
import com.coders.rentkun.dtos.vehicles.responses.BrandResponseDto;
import com.coders.rentkun.dtos.vehicles.responses.ModelResponseDto;

import java.util.List;

public interface VehicleModelService {
    ModelResponseDto saveModel(CreateModelRequestDto modelRequestDto);

    List<BrandModelResponseDto> getAllModelsWithBrands();

    List<BrandModelResponseDto> getAllModelsWithActiveBrands();
    
    ModelResponseDto getModelByModelId(Long modelId);

    ModelResponseDto getModelByModelName(String modelName);

    List<BrandModelResponseDto> getModelsByBrandId(Long brandId);

    List<BrandModelResponseDto> getModelsByBrandName(String brandName);


    ModelResponseDto updateModel(Long modelId, UpdateModelRequestDto modelRequestDto);

    void deleteBrand(Long modelId);
}
