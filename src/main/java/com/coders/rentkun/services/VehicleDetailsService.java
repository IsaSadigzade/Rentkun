package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleDetailsRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateVehicleDetailsRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.VehicleDetailsResponseDto;

import java.util.List;

public interface VehicleDetailsService {
    VehicleDetailsResponseDto saveVehicleDetails(CreateVehicleDetailsRequestDto vehicleDetailsRequestDto);

    List<VehicleDetailsResponseDto> getAllVehicleDetails();

    VehicleDetailsResponseDto getVehicleDetailsByVehicleDetailsId(Long vehicleDetailsId);

    VehicleDetailsResponseDto updateVehicleDetails(Long vehicleDetailsId, UpdateVehicleDetailsRequestDto vehicleDetailsRequestDto);

    void deleteVehicleDetails(Long vehicleDetailsId);
}
