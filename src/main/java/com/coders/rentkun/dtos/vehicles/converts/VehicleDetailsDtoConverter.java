package com.coders.rentkun.dtos.vehicles.converts;

import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleDetailsRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateVehicleDetailsRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.VehicleDetailsResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VehicleDetailsDtoConverter {
    public VehicleDetailsResponseDto convertToResponse(VehicleDetails entity) {
        return new VehicleDetailsResponseDto(
                entity.getId(),
                entity.getCity(),
                entity.getCountry(),
                entity.getPlateNumber(),
                entity.getNumberOfSeats(),
                entity.getDistance(),
                entity.getColor(),
                entity.getYear(),
                entity.getDescription()
        );
    }

    public VehicleDetails convertToEntity(CreateVehicleDetailsRequestDto vehicleDetailsRequestDto) {
        return new VehicleDetails(
                vehicleDetailsRequestDto.getCity(),
                vehicleDetailsRequestDto.getCountry(),
                vehicleDetailsRequestDto.getPlateNumber(),
                vehicleDetailsRequestDto.getNumberOfSeats(),
                vehicleDetailsRequestDto.getDistance(),
                vehicleDetailsRequestDto.getColor(),
                vehicleDetailsRequestDto.getYear(),
                vehicleDetailsRequestDto.getDescription(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public VehicleDetails convertToEntity(VehicleDetails foundVehicleDetails, UpdateVehicleDetailsRequestDto vehicleDetailsRequestDto) {
        foundVehicleDetails.setCity(vehicleDetailsRequestDto.getCity());
        foundVehicleDetails.setCountry(vehicleDetailsRequestDto.getCountry());
        foundVehicleDetails.setPlateNumber(vehicleDetailsRequestDto.getPlateNumber());
        foundVehicleDetails.setNumberOfSeats(vehicleDetailsRequestDto.getNumberOfSeats());
        foundVehicleDetails.setDistance(vehicleDetailsRequestDto.getDistance());
        foundVehicleDetails.setColor(vehicleDetailsRequestDto.getColor());
        foundVehicleDetails.setYear(vehicleDetailsRequestDto.getYear());
        foundVehicleDetails.setDescription(vehicleDetailsRequestDto.getDescription());
        foundVehicleDetails.setUpdatedAt(LocalDateTime.now());
        return foundVehicleDetails;
    }
}
