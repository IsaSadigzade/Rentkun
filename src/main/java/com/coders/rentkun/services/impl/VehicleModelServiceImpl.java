package com.coders.rentkun.services.impl;

import com.coders.rentkun.dtos.vehicles.converts.BrandModelDtoConverter;
import com.coders.rentkun.dtos.vehicles.requests.CreateModelRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateModelRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.BrandModelResponseDto;
import com.coders.rentkun.dtos.vehicles.responses.ModelResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleBrand;
import com.coders.rentkun.entities.vehicles.VehicleModel;
import com.coders.rentkun.exception.BrandNotFoundException;
import com.coders.rentkun.exception.ModelNotFoundException;
import com.coders.rentkun.repositories.VehicleModelRepository;
import com.coders.rentkun.services.VehicleModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleModelServiceImpl implements VehicleModelService {
    private final VehicleModelRepository vehicleModelRepository;
    private final VehicleBrandServiceImpl vehicleBrandService;
    private final BrandModelDtoConverter brandModelDtoConverter;

    public VehicleModelServiceImpl(VehicleModelRepository vehicleModelRepository,
                                   VehicleBrandServiceImpl vehicleBrandService,
                                   BrandModelDtoConverter brandModelDtoConverter) {
        this.vehicleModelRepository = vehicleModelRepository;
        this.vehicleBrandService = vehicleBrandService;
        this.brandModelDtoConverter = brandModelDtoConverter;
    }

    @Override
    public ModelResponseDto saveModel(CreateModelRequestDto modelRequestDto) {
        VehicleBrand brand = vehicleBrandService.findBrandByBrandName(modelRequestDto.getBrandName());
        return brandModelDtoConverter.convertToModelResponse(
                vehicleModelRepository.save(
                        brandModelDtoConverter.convertFromRequestToEntity(brand, modelRequestDto)
                )
        );
    }

    @Override
    public List<BrandModelResponseDto> getAllModelsWithBrands() {
        return vehicleModelRepository.findAll().stream()
                .map(brandModelDtoConverter::convertToBrandModelResponse)
                .toList();
    }

    @Override
    public List<BrandModelResponseDto> getAllModelsWithActiveBrands() {
        List<BrandModelResponseDto> responses = vehicleModelRepository.findAll().stream()
                .map(brandModelDtoConverter::convertToBrandModelResponse)
                .toList();

        return responses.stream()
                .filter(model -> isBrandActive(model.getBrandName()))
                .collect(Collectors.toList());
    }

    @Override
    public ModelResponseDto getModelByModelId(Long modelId) {
        return brandModelDtoConverter.convertToModelResponse(findModelById(modelId));
    }

    @Override
    public ModelResponseDto getModelByModelName(String modelName) {
        return brandModelDtoConverter.convertToModelResponse(findModelByModelName(modelName));
    }

    @Override
    public List<BrandModelResponseDto> getModelsByBrandId(Long brandId) {
        List<VehicleModel> vehicleModels = vehicleModelRepository.findByVehicleBrandId(brandId);
        return vehicleModels.stream()
                .map(brandModelDtoConverter::convertToBrandModelResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BrandModelResponseDto> getModelsByBrandName(String brandName) {
        List<VehicleModel> vehicleModels = vehicleModelRepository.findByVehicleBrandName(brandName);
        return vehicleModels.stream()
                .map(brandModelDtoConverter::convertToBrandModelResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ModelResponseDto updateModel(Long modelId, UpdateModelRequestDto modelRequestDto) {
        return brandModelDtoConverter.convertToModelResponse(
                vehicleModelRepository.save(
                        brandModelDtoConverter.convertFromRequestToEntity(findModelById(modelId), modelRequestDto)
                )
        );
    }

    @Override
    public void deleteBrand(Long modelId) {
        findModelById(modelId);
        vehicleModelRepository.deleteById(modelId);
    }


    public VehicleModel findModelById(Long modelId) {
        return vehicleModelRepository.findById(modelId)
                .orElseThrow(() -> new ModelNotFoundException("Model couldn't be found by following id: " + modelId));
    }

    public VehicleModel findModelByModelName(String modelName) {
        return vehicleModelRepository.findByName(modelName)
                .orElseThrow(() -> new BrandNotFoundException("Model couldn't be found by following modelName: " + modelName));
    }

    private boolean isBrandActive(String brandName) {
        VehicleBrand foundBrand = vehicleBrandService.findBrandByBrandName(brandName);
        return foundBrand != null && foundBrand.isActive();
    }
}
