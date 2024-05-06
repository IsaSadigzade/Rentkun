package com.coders.rentkun.dtos.vehicles.converts;

import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleRequestByIDs;
import com.coders.rentkun.dtos.vehicles.responses.VehicleResponseDto;
import com.coders.rentkun.entities.vehicles.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class VehicleDtoConverter {

    private final VehicleDetailsDtoConverter vehicleDetailsDtoConverter;
    private final BrandDtoConverter brandDtoConverter;
    private final BrandModelDtoConverter brandModelDtoConverter;
    private final VehicleTypeDtoConverter vehicleTypeDtoConverter;
    private final GearboxTypeDtoConverter gearboxTypeDtoConverter;
    private final FuelTypeDtoConverter fuelTypeDtoConverter;
    private final LogoDtoConverter logoDtoConverter;
    private final FeatureDtoConverter featureDtoConverter;

    public VehicleDtoConverter(VehicleDetailsDtoConverter vehicleDetailsDtoConverter,
                               BrandDtoConverter brandDtoConverter,
                               BrandModelDtoConverter brandModelDtoConverter,
                               VehicleTypeDtoConverter vehicleTypeDtoConverter,
                               GearboxTypeDtoConverter gearboxTypeDtoConverter,
                               FuelTypeDtoConverter fuelTypeDtoConverter,
                               LogoDtoConverter logoDtoConverter,
                               FeatureDtoConverter featureDtoConverter) {
        this.vehicleDetailsDtoConverter = vehicleDetailsDtoConverter;
        this.brandDtoConverter = brandDtoConverter;
        this.brandModelDtoConverter = brandModelDtoConverter;
        this.vehicleTypeDtoConverter = vehicleTypeDtoConverter;
        this.gearboxTypeDtoConverter = gearboxTypeDtoConverter;
        this.fuelTypeDtoConverter = fuelTypeDtoConverter;
        this.logoDtoConverter = logoDtoConverter;
        this.featureDtoConverter = featureDtoConverter;
    }

    public VehicleResponseDto convertToResponse(Vehicle vehicle) {
        return new VehicleResponseDto(
                vehicle.getId(),
                vehicleDetailsDtoConverter.convertToResponse(vehicle.getVehicleDetails()),
                brandDtoConverter.convertToResponse(vehicle.getVehicleBrand()),
                brandModelDtoConverter.convertToModelResponse(vehicle.getVehicleModel()),
                vehicleTypeDtoConverter.convertToResponse(vehicle.getVehicleType()),
                gearboxTypeDtoConverter.convertToResponse(vehicle.getVehicleGearboxType()),
                fuelTypeDtoConverter.convertToResponse(vehicle.getVehicleFuelType()),
                logoDtoConverter.convertToResponse(vehicle.getVehicleLogo()),
                featureDtoConverter.convertToSetResponse(vehicle.getVehicleFeatures())
        );
    }

    public Vehicle convertToEntity(VehicleDetails savedDetails,
                                   VehicleBrand foundBrand,
                                   VehicleModel foundModel,
                                   VehicleType foundType,
                                   VehicleGearboxType foundGearbox,
                                   VehicleFuelType foundFuelType,
                                   VehicleLogo foundLogo,
                                   Set<VehicleFeature> foundFeatures) {

        Vehicle entity = new Vehicle();

        entity.setVehicleDetails(savedDetails);
        entity.setVehicleBrand(foundBrand);
        entity.setVehicleModel(foundModel);
        entity.setVehicleType(foundType);
        entity.setVehicleGearboxType(foundGearbox);
        entity.setVehicleFuelType(foundFuelType);
        entity.setVehicleLogo(foundLogo);
        entity.setVehicleFeatures(foundFeatures);

        return entity;
    }

    public Vehicle convertToEntity(Vehicle foundVehicle, CreateVehicleRequestByIDs vehicleRequestByIDs) {
//        foundVehicle.setName(vehicleRequestByIDs.getFuelTypeName());
        return foundVehicle;
    }
}
