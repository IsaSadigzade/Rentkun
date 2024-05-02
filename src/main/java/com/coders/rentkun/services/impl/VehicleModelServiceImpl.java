package com.coders.rentkun.services.impl;

import com.coders.rentkun.dtos.vehicles.converts.BrandModelDtoConverter;
import com.coders.rentkun.dtos.vehicles.requests.CreateModelRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateModelRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.BrandModelResponseDto;
import com.coders.rentkun.dtos.vehicles.responses.ModelResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleBrand;
import com.coders.rentkun.entities.vehicles.VehicleModel;
import com.coders.rentkun.exception.BrandNotFoundException;
import com.coders.rentkun.exception.ModelDoesNotExistException;
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
                .collect(Collectors.toList());
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
        if (!isModelExist(modelId)) {
            vehicleModelRepository.deleteById(modelId);
        } else {
            throw new ModelDoesNotExistException("Model doesn't exist by following modelId: " + modelId);
        }
    }

    @Override
    public void deactivateModelByModelId(Long modelId) {
        VehicleModel model = findModelById(modelId);
        model.setActive(false);
        vehicleModelRepository.save(model);
    }

    @Override
    public void deactivateModelByModelName(String modelName) {
        VehicleModel model = findModelByModelName(modelName);
        model.setActive(false);
        vehicleModelRepository.save(model);
    }

    @Override
    public void activateModelByModelId(Long modelId) {
        VehicleModel model = findModelById(modelId);
        model.setActive(true);
        vehicleModelRepository.save(model);
    }

    @Override
    public void activateModelByModelName(String modelName) {
        VehicleModel model = findModelByModelName(modelName);
        model.setActive(true);
        vehicleModelRepository.save(model);
    }


    public VehicleModel findModelById(Long modelId) {
        return vehicleModelRepository.findById(modelId)
                .orElseThrow(() -> new ModelNotFoundException("Model couldn't be found by following id: " + modelId));
    }

    public VehicleModel findModelByModelName(String modelName) {
        return vehicleModelRepository.findByName(modelName)
                .orElseThrow(() -> new BrandNotFoundException("Model couldn't be found by following modelName: " + modelName));
    }

    private boolean isModelExist(Long modelId) {
        return vehicleModelRepository.existsById(modelId);
    }

    private boolean isBrandActive(String brandName) {
        VehicleBrand foundBrand = vehicleBrandService.findBrandByBrandName(brandName);
        return foundBrand != null && foundBrand.isActive();
    }
}
