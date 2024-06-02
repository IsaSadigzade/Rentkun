package com.coders.rentkun.dtos.vehicles.responses;

import com.coders.rentkun.entities.common.enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponseByData {
    private Long id;
    private VehicleDetailsResponseDto vehicleDetails;
    private BrandModelResponseDto brandModel;
    private VehicleTypeResponseDto vehicleType;
    private GearboxTypeResponseDto gearboxType;
    private FuelTypeResponseDto fuelType;
    private List<FeatureResponseDto> features;
    private LogoResponseDto logo;
//    private ImagesResponseDto images;
    private boolean available;
    private RentalStatus rentalStatus;
    private LocalDate availableFromDate;
    private LocalDate availableToDate;
}