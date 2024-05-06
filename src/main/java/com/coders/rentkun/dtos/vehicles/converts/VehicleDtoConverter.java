package com.coders.rentkun.dtos.vehicles.converts;

import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleRequestByIDs;
import com.coders.rentkun.dtos.vehicles.responses.VehicleResponseDto;
import com.coders.rentkun.entities.vehicles.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleDtoConverter {

    public VehicleResponseDto convertToResponse(Vehicle entity) {
        return new VehicleResponseDto(
        );
    }

    public Vehicle convertToEntity(CreateVehicleRequestByIDs createVehicleRequestByIDs) {
        return new Vehicle(

        );
    }

    public Vehicle convertToEntity(Vehicle foundVehicle, CreateVehicleRequestByIDs vehicleRequestByIDs) {
//        foundVehicle.setName(vehicleRequestByIDs.getFuelTypeName());
        return foundVehicle;
    }
}
