package com.coders.rentkun.controllers;

import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleRequestByIDs;
import com.coders.rentkun.dtos.vehicles.responses.VehicleResponseDto;
import com.coders.rentkun.services.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @PostMapping
    public ResponseEntity<VehicleResponseDto> saveVehicle(@RequestBody CreateVehicleRequestByIDs vehicleRequestDto) {
        return ResponseEntity.ok(vehicleService.saveVehicle(vehicleRequestDto));
    }
}
