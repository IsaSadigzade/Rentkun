package com.coders.rentkun.dtos.vehicles.converts;

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

    public Vehicle convertToEntity(VehicleDetails foundDetails,
                                   Object[] objects,
                                   Set<VehicleFeature> features) {

        return new Vehicle(
                foundDetails,
                (VehicleBrand) objects[0],
                (VehicleModel) objects[1],
                (VehicleType) objects[2],
                (VehicleGearboxType) objects[3],
                (VehicleFuelType) objects[4],
                (VehicleLogo) objects[5],
                features
        );
    }


    public Vehicle convertToEntity(Vehicle foundVehicle,
                                   VehicleDetails foundDetails,
                                   Object[] objects,
                                   Set<VehicleFeature> foundFeatures) {
        foundVehicle.setVehicleDetails(foundDetails);
        foundVehicle.setVehicleBrand((VehicleBrand) objects[0]);
        foundVehicle.setVehicleModel((VehicleModel) objects[1]);
        foundVehicle.setVehicleType((VehicleType) objects[2]);
        foundVehicle.setVehicleGearboxType((VehicleGearboxType) objects[3]);
        foundVehicle.setVehicleFuelType((VehicleFuelType) objects[4]);
        foundVehicle.setVehicleLogo((VehicleLogo) objects[5]);
        foundVehicle.setVehicleFeatures(foundFeatures);
        return foundVehicle;
    }
}
