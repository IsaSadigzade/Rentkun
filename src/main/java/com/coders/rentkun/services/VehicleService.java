package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.converts.VehicleDtoConverter;
import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleRequestByIDs;
import com.coders.rentkun.dtos.vehicles.responses.VehicleResponseDto;
import com.coders.rentkun.entities.vehicles.*;
import com.coders.rentkun.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleBrandService vehicleBrandService;
    private final VehicleModelService vehicleModelService;
    private final VehicleTypeService vehicleTypeService;
    private final VehicleGearboxTypeService vehicleGearboxTypeService;
    private final VehicleFuelTypeService vehicleFuelTypeService;
    private final VehicleLogoService vehicleLogoService;
    private final VehicleDtoConverter vehicleDtoConverter;
    private final VehicleFeaturesService vehicleFeaturesService;
    private final VehicleDetailsService vehicleDetailsService;

    public VehicleService(VehicleRepository vehicleRepository, VehicleBrandService vehicleBrandService, VehicleModelService vehicleModelService, VehicleTypeService vehicleTypeService, VehicleGearboxTypeService vehicleGearboxTypeService, VehicleFuelTypeService vehicleFuelTypeService, VehicleLogoService vehicleLogoService, VehicleDtoConverter vehicleDtoConverter, VehicleFeaturesService vehicleFeaturesService, VehicleDetailsService vehicleDetailsService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleBrandService = vehicleBrandService;
        this.vehicleModelService = vehicleModelService;
        this.vehicleTypeService = vehicleTypeService;
        this.vehicleGearboxTypeService = vehicleGearboxTypeService;
        this.vehicleFuelTypeService = vehicleFuelTypeService;
        this.vehicleLogoService = vehicleLogoService;
        this.vehicleDtoConverter = vehicleDtoConverter;
        this.vehicleFeaturesService = vehicleFeaturesService;
        this.vehicleDetailsService = vehicleDetailsService;
    }

    public VehicleResponseDto saveVehicle(CreateVehicleRequestByIDs vehicleRequestDto) {
        VehicleDetails savedVehicleDetails = vehicleDetailsService.save(vehicleRequestDto.getDetailsRequest());
        VehicleBrand foundBrand = vehicleBrandService.findBrandById(vehicleRequestDto.getBrandId());
        VehicleModel foundModel = vehicleModelService.findModelById(vehicleRequestDto.getModelId());
        VehicleType foundType = vehicleTypeService.findByVehicleTypeId(vehicleRequestDto.getVehicleTypeId());
        VehicleGearboxType foundGearbox = vehicleGearboxTypeService.findByGearboxTypeId(vehicleRequestDto.getGearboxTypeId());
        VehicleFuelType foundFuelType = vehicleFuelTypeService.findByFuelTypeId(vehicleRequestDto.getFuelTypeId());
        VehicleLogo foundLogo = vehicleLogoService.findByVehicleLogoId(vehicleRequestDto.getLogoId());
        Set<VehicleFeature> foundFeatures = vehicleFeaturesService.findFeaturesByIds(vehicleRequestDto.getFeatureIds());


        return vehicleDtoConverter.convertToResponse(
                vehicleRepository.save(
                        vehicleDtoConverter.convertToEntity(
                                savedVehicleDetails,
                                foundBrand,
                                foundModel,
                                foundType,
                                foundGearbox,
                                foundFuelType,
                                foundLogo,
                                foundFeatures
                        )
                )
        );
    }

    protected Vehicle findByVehicleId(Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }
}
