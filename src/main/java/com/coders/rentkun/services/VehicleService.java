package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.converts.VehicleDtoConverter;
import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateVehicleRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.VehicleRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.VehicleResponseDto;
import com.coders.rentkun.entities.vehicles.*;
import com.coders.rentkun.repositories.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Transactional
    public VehicleResponseDto saveVehicle(CreateVehicleRequestDto vehicleRequestDto) {
        VehicleDetails savedVehicleDetails = vehicleDetailsService.save(vehicleRequestDto.getDetailsRequest());
        VehicleDetails foundDetails = vehicleDetailsService.findByVehicleDetailsId(savedVehicleDetails.getId());
        Object[] objects = findAllDetailsOfVehicle(vehicleRequestDto);
        Set<VehicleFeature> foundFeatures = vehicleFeaturesService.findFeaturesByIds(vehicleRequestDto.getFeatureIds());
        return vehicleDtoConverter.convertToResponse(
                vehicleRepository.save(
                        vehicleDtoConverter.convertToEntity(
                                foundDetails,
                                objects,
                                foundFeatures
                        )
                )
        );
    }

    public List<VehicleResponseDto> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(vehicleDtoConverter::convertToResponse)
                .collect(Collectors.toList());
    }

    public VehicleResponseDto getVehicleById(Long vehicleId) {
        return vehicleDtoConverter.convertToResponse(findByVehicleId(vehicleId));
    }

    public VehicleResponseDto updateVehicle(Long vehicleId, UpdateVehicleRequestDto vehicleRequestDto) {
        Vehicle foundVehicle = findByVehicleId(vehicleId);
        vehicleDetailsService.updateVehicleDetails(foundVehicle.getVehicleDetails().getId(), vehicleRequestDto.getDetailsRequest());
        VehicleDetails foundDetails = vehicleDetailsService.findByVehicleDetailsId(foundVehicle.getVehicleDetails().getId());

        Object[] objects = findAllDetailsOfVehicle(vehicleRequestDto);
        Set<VehicleFeature> foundFeatures = vehicleFeaturesService.findFeaturesByIds(vehicleRequestDto.getFeatureIds());

        return vehicleDtoConverter.convertToResponse(
                vehicleRepository.save(
                        vehicleDtoConverter.convertToEntity(
                                foundVehicle,
                                foundDetails,
                                objects,
                                foundFeatures
                        )
                )
        );

    }

    public void deleteVehicle(Long vehicleId) {
        if (isVehicleExist(vehicleId)) {
            vehicleRepository.deleteById(vehicleId);
        } else {
            throw new RuntimeException("Vehicle doesn't exist with id: " + vehicleId);
        }
    }

    protected Vehicle findByVehicleId(Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    protected boolean isVehicleExist(Long vehicleId) {
        return vehicleRepository.existsById(vehicleId);
    }

    protected Object[] findAllDetailsOfVehicle(VehicleRequestDto vehicleRequestDto) {
        VehicleBrand foundBrand = vehicleBrandService.findBrandById(vehicleRequestDto.getBrandId());
        VehicleModel foundModel = vehicleModelService.findModelById(vehicleRequestDto.getModelId());
        VehicleType foundType = vehicleTypeService.findByVehicleTypeId(vehicleRequestDto.getVehicleTypeId());
        VehicleGearboxType foundGearbox = vehicleGearboxTypeService.findByGearboxTypeId(vehicleRequestDto.getGearboxTypeId());
        VehicleFuelType foundFuelType = vehicleFuelTypeService.findByFuelTypeId(vehicleRequestDto.getFuelTypeId());
        VehicleLogo foundLogo = vehicleLogoService.findByVehicleLogoId(vehicleRequestDto.getLogoId());
        return new Object[]{foundBrand, foundModel, foundType, foundGearbox, foundFuelType, foundLogo};
    }
}
