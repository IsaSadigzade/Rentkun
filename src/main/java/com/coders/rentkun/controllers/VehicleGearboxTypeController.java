package com.coders.rentkun.controllers;

import com.coders.rentkun.repositories.VehicleGearboxTypeRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle/vehicle-gearbox-type")
public class VehicleGearboxTypeController {
    private final VehicleGearboxTypeRepository vehicleGearboxTypeRepository;

    public VehicleGearboxTypeController(VehicleGearboxTypeRepository vehicleGearboxTypeRepository) {
        this.vehicleGearboxTypeRepository = vehicleGearboxTypeRepository;
    }
}
