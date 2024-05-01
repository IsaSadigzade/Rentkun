package com.coders.rentkun.controllers;

import com.coders.rentkun.services.VehicleFeaturesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle/vehicle-features")
public class VehicleFeaturesController {
    private final VehicleFeaturesService vehicleFeaturesService;

    public VehicleFeaturesController(VehicleFeaturesService vehicleFeaturesService) {
        this.vehicleFeaturesService = vehicleFeaturesService;
    }
}
