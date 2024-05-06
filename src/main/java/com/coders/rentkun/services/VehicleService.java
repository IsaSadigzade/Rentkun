package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleRequestByIDs;
import com.coders.rentkun.dtos.vehicles.responses.VehicleResponseDto;
import com.coders.rentkun.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public VehicleResponseDto saveVehicle(CreateVehicleRequestByIDs vehicleRequestDto) {
        return null;
    }
}
