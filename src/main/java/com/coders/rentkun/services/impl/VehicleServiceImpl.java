package com.coders.rentkun.services.impl;

import com.coders.rentkun.entities.vehicles.Vehicle;
import com.coders.rentkun.repositories.VehicleRepository;
import com.coders.rentkun.services.VehicleService;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }
}
