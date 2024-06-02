package com.coders.rentkun.services.vehicles;

import com.coders.rentkun.dtos.vehicles.converts.BrandModelDtoConverter;
import com.coders.rentkun.dtos.vehicles.requests.CreateModelRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateModelRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.BrandModelResponseDto;
import com.coders.rentkun.dtos.vehicles.responses.ModelResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleBrand;
import com.coders.rentkun.entities.vehicles.VehicleModel;
import com.coders.rentkun.exception.ModelDoesNotExistException;
import com.coders.rentkun.exception.ModelNotFoundException;
import com.coders.rentkun.repositories.vehicles.VehicleModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleModelService {
    private final VehicleModelRepository vehicleModelRepository;
    private final VehicleBrandService vehicleBrandService;
    private final BrandModelDtoConverter brandModelDtoConverter;

    public VehicleModelService(VehicleModelRepository vehicleModelRepository,
                               VehicleBrandService vehicleBrandService,
                               BrandModelDtoConverter brandModelDtoConverter) {
        this.vehicleModelRepository = vehicleModelRepository;
        this.vehicleBrandService = vehicleBrandService;
        this.brandModelDtoConverter = brandModelDtoConverter;
    }

    public ModelResponseDto saveModel(CreateModelRequestDto modelRequestDto) {
        VehicleBrand brand = vehicleBrandService.findBrandByBrandName(modelRequestDto.getBrandName());
        return brandModelDtoConverter.convertToModelResponse(
                vehicleModelRepository.save(
                        brandModelDtoConverter.convertFromRequestToEntity(brand, modelRequestDto)
                )
        );
    }

    public List<BrandModelResponseDto> getAllModelsWithBrands() {
        return vehicleModelRepository.findAll().stream()
                .map(brandModelDtoConverter::convertToBrandModelResponse)
                .collect(Collectors.toList());
    }

    public List<BrandModelResponseDto> getAllModelsWithActiveBrands() {
        List<BrandModelResponseDto> responses = vehicleModelRepository.findAll().stream()
                .map(brandModelDtoConverter::convertToBrandModelResponse)
                .toList();

        return responses.stream()
                .filter(model -> isBrandActive(model.getBrandName()))
                .collect(Collectors.toList());
    }

    public ModelResponseDto getModelByModelId(Long modelId) {
        return brandModelDtoConverter.convertToModelResponse(findModelById(modelId));
    }

    public ModelResponseDto getModelByModelName(String modelName) {
        return brandModelDtoConverter.convertToModelResponse(findModelByModelName(modelName));
    }

    public List<BrandModelResponseDto> getModelsByBrandId(Long brandId) {
        List<VehicleModel> vehicleModels = vehicleModelRepository.findByVehicleBrandId(brandId);
        return vehicleModels.stream()
                .map(brandModelDtoConverter::convertToBrandModelResponse)
                .collect(Collectors.toList());
    }

    public List<BrandModelResponseDto> getModelsByBrandName(String brandName) {
        List<VehicleModel> vehicleModels = vehicleModelRepository.findByVehicleBrandName(brandName);
        return vehicleModels.stream()
                .map(brandModelDtoConverter::convertToBrandModelResponse)
                .collect(Collectors.toList());
    }

    public ModelResponseDto updateModel(Long modelId, UpdateModelRequestDto modelRequestDto) {
        return brandModelDtoConverter.convertToModelResponse(
                vehicleModelRepository.save(
                        brandModelDtoConverter.convertFromRequestToEntity(findModelById(modelId), modelRequestDto)
                )
        );
    }

    public void deleteBrand(Long modelId) {
        if (!isModelExist(modelId)) {
            vehicleModelRepository.deleteById(modelId);
        } else {
            throw new ModelDoesNotExistException("Model doesn't exist by following modelId: " + modelId);
        }
    }

    public void deactivateModelByModelId(Long modelId) {
        VehicleModel model = findModelById(modelId);
        model.setActive(false);
        vehicleModelRepository.save(model);
    }

    public void deactivateModelByModelName(String modelName) {
        VehicleModel model = findModelByModelName(modelName);
        model.setActive(false);
        vehicleModelRepository.save(model);
    }

    public void activateModelByModelId(Long modelId) {
        VehicleModel model = findModelById(modelId);
        model.setActive(true);
        vehicleModelRepository.save(model);
    }

    public void activateModelByModelName(String modelName) {
        VehicleModel model = findModelByModelName(modelName);
        model.setActive(true);
        vehicleModelRepository.save(model);
    }


    protected VehicleModel findModelById(Long modelId) {
        return vehicleModelRepository.findById(modelId)
                .orElseThrow(() -> new ModelNotFoundException("Model couldn't be found by following id: " + modelId));
    }

    protected VehicleModel findModelByModelName(String modelName) {
        return vehicleModelRepository.findByName(modelName)
                .orElseThrow(() -> new ModelNotFoundException("Model couldn't be found by following modelName: " + modelName));
    }

    protected boolean isModelExist(Long modelId) {
        return vehicleModelRepository.existsById(modelId);
    }

    protected boolean isBrandActive(String brandName) {
        VehicleBrand foundBrand = vehicleBrandService.findBrandByBrandName(brandName);
        return foundBrand != null && foundBrand.isActive();
    }
}
