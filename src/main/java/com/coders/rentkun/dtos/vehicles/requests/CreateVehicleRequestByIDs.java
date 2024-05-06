package com.coders.rentkun.dtos.vehicles.requests;

import lombok.Data;

import java.util.Set;

@Data
public class CreateVehicleRequestByIDs {
    private Long brandId;
    private Long modelId;
    private Long vehicleDetailsId;
    private Long vehicleTypeId;
    private Long gearboxTypeId;
    private Long fuelTypeId;
    private Long logoId;
    private Set<Long> featureIds;
    private String city;
    private String country;
    private String plateNumber;
    private String numberOfSeats;
    private String distance;
    private String color;
    private String year;
    private String description;
}
