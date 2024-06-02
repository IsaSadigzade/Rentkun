package com.coders.rentkun.controllers;

import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateVehicleRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.VehicleResponseDto;
import com.coders.rentkun.services.vehicles.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<VehicleResponseDto> saveVehicle(@RequestBody CreateVehicleRequestDto vehicleRequestDto) {
        return ResponseEntity.ok(vehicleService.saveVehicle(vehicleRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponseDto>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponseDto> getVehicleById(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(vehicleService.getVehicleById(vehicleId));
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponseDto> updateVehicle(@PathVariable Long vehicleId, @RequestBody UpdateVehicleRequestDto vehicleRequestDto) {
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicleId, vehicleRequestDto));
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.ok().build();
    }
}
