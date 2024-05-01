package com.coders.rentkun.controllers;

import com.coders.rentkun.services.VehicleLogoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle/vehicle-logo")
public class VehicleLogoController {
    private final VehicleLogoService vehicleLogoService;

    public VehicleLogoController(VehicleLogoService vehicleLogoService) {
        this.vehicleLogoService = vehicleLogoService;
    }
}
