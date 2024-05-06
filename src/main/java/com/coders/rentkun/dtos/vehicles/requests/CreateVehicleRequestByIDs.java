package com.coders.rentkun.dtos.vehicles.requests;

import lombok.Data;

import java.util.Set;

@Data
public class CreateVehicleRequestByIDs {
    private CreateVehicleDetailsRequestDto detailsRequest;
    private Long brandId;
    private Long modelId;
    private Long vehicleTypeId;
    private Long gearboxTypeId;
    private Long fuelTypeId;
    private Long logoId;
    private Set<Long> featureIds;
}
