package com.coders.rentkun.dtos.vehicles.responses;

import com.coders.rentkun.enums.common.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponseDto {
    private Long id;
    private Long detailsId;
    private Long brandId;
    private Long modelId;
    private Long vehicleTypeId;
    private Long gearboxId;
    private Long fuelTypeId;
    private String city;
    private String country;
    private String plateNumber;
    private String numberOfSeats;
    private String distance;
    private String color;
    private String year;
    private String description;
    private String logoUrl;
    private Set<String> imagesUrl;
    private Set<FeatureResponseDto> features;

    private boolean available;
    private RentalStatus rentalStatus;
    private LocalDate availableFromDate;
    private LocalDate availableToDate;
}