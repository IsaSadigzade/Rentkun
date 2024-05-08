package com.coders.rentkun.dtos.vehicles.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateVehicleRequestDto extends VehicleRequestDto{
    private UpdateVehicleDetailsRequestDto detailsRequest;
//    private Long brandId;
//    private Long modelId;
//    private Long vehicleTypeId;
//    private Long gearboxTypeId;
//    private Long fuelTypeId;
//    private Long logoId;
//    private Set<Long> featureIds;
}
