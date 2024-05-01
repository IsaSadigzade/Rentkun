package com.coders.rentkun.controllers;

import com.coders.rentkun.services.VehicleFuelTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle/vehicle-fuel-type")
public class VehicleFuelTypeController {
    private final VehicleFuelTypeService vehicleFuelTypeService;

    public VehicleFuelTypeController(VehicleFuelTypeService vehicleFuelTypeService) {
        this.vehicleFuelTypeService = vehicleFuelTypeService;
    }
}
