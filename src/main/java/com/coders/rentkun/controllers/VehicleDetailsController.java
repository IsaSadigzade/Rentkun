package com.coders.rentkun.controllers;

import com.coders.rentkun.services.VehicleDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle/vehicle-details")
public class VehicleDetailsController {
    private final VehicleDetailsService vehicleDetailsService;

    public VehicleDetailsController(VehicleDetailsService vehicleDetailsService) {
        this.vehicleDetailsService = vehicleDetailsService;
    }
}
