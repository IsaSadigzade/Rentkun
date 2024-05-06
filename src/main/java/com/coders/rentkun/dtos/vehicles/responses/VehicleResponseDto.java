package com.coders.rentkun.dtos.vehicles.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponseDto {
    private Long id;
    private VehicleDetailsResponseDto detailsResponseDto;
    private BrandResponseDto brandResponseDto;
    private ModelResponseDto modelResponseDto;
    private VehicleTypeResponseDto vehicleTypeResponseDto;
    private GearboxTypeResponseDto gearboxTypeResponseDto;
    private FuelTypeResponseDto fuelTypeResponseDto;
    private LogoResponseDto logoResponseDto;
//    private Set<String> imagesUrl;
    private Set<FeatureResponseDto> features;
}