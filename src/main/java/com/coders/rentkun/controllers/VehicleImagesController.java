package com.coders.rentkun.controllers;

import com.coders.rentkun.services.VehicleImagesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle/images")
public class VehicleImagesController {
    private final VehicleImagesService vehicleImagesService;

    public VehicleImagesController(VehicleImagesService vehicleImagesService) {
        this.vehicleImagesService = vehicleImagesService;
    }
}
