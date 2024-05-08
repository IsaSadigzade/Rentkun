package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.converts.VehicleDetailsDtoConverter;
import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleDetailsRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateVehicleDetailsRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.VehicleDetailsResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleDetails;
import com.coders.rentkun.exception.FuelTypeNotFoundException;
import com.coders.rentkun.exception.VehicleTypeDoesNotExistException;
import com.coders.rentkun.repositories.VehicleDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleDetailsService {

    private final VehicleDetailsRepository vehicleDetailsRepository;
    private final VehicleDetailsDtoConverter vehicleDetailsDtoConverter;

    public VehicleDetailsService(VehicleDetailsRepository vehicleDetailsRepository, VehicleDetailsDtoConverter vehicleDetailsDtoConverter) {
        this.vehicleDetailsRepository = vehicleDetailsRepository;
        this.vehicleDetailsDtoConverter = vehicleDetailsDtoConverter;
    }

    public VehicleDetailsResponseDto saveVehicleDetails(CreateVehicleDetailsRequestDto vehicleDetailsRequestDto) {
        return vehicleDetailsDtoConverter.convertToResponse(
                vehicleDetailsRepository.save(
                        vehicleDetailsDtoConverter.convertToEntity(vehicleDetailsRequestDto)
                )
        );
    }

    public List<VehicleDetailsResponseDto> getAllVehicleDetails() {
        return vehicleDetailsRepository.findAll().stream()
                .map(vehicleDetailsDtoConverter::convertToResponse)
                .collect(Collectors.toList());
    }

    public VehicleDetailsResponseDto getVehicleDetailsByVehicleDetailsId(Long vehicleDetailsId) {
        return vehicleDetailsDtoConverter.convertToResponse(findByVehicleDetailsId(vehicleDetailsId));
    }

    public VehicleDetailsResponseDto updateVehicleDetails(Long vehicleDetailsId, UpdateVehicleDetailsRequestDto vehicleDetailsRequestDto) {
        return vehicleDetailsDtoConverter.convertToResponse(
                vehicleDetailsRepository.save(
                        vehicleDetailsDtoConverter.convertToEntity(findByVehicleDetailsId(vehicleDetailsId), vehicleDetailsRequestDto)
                )
        );
    }

    public void deleteVehicleDetails(Long vehicleDetailsId) {
        if (isVehicleDetailsExist(vehicleDetailsId)) {
            vehicleDetailsRepository.deleteById(vehicleDetailsId);
        } else {
            throw new VehicleTypeDoesNotExistException("Vehicle Details doesn't exist by following vehicleDetailsId: " + vehicleDetailsId);
        }
    }

    protected VehicleDetails findByVehicleDetailsId(Long vehicleDetailsId) {
        return vehicleDetailsRepository.findById(vehicleDetailsId)
                .orElseThrow(() -> new FuelTypeNotFoundException("Vehicle Details couldn't be found by following id: " + vehicleDetailsId));
    }

    protected boolean isVehicleDetailsExist(Long vehicleDetailsId) {
        return vehicleDetailsRepository.existsById(vehicleDetailsId);
    }

    protected VehicleDetails save(CreateVehicleDetailsRequestDto vehicleDetailsRequestDto) {
        VehicleDetails convertedDetails = vehicleDetailsDtoConverter.convertToEntity(vehicleDetailsRequestDto);
        return vehicleDetailsRepository.save(
                convertedDetails
        );
    }

}
