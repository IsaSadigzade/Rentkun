package com.coders.rentkun.dtos.vehicles.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class VehicleRequestDto {
    private Long brandId;
    private Long modelId;
    private Long vehicleTypeId;
    private Long gearboxTypeId;
    private Long fuelTypeId;
    private Long logoId;
    private Set<Long> featureIds;
}
