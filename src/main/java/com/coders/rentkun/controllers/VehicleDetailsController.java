package com.coders.rentkun.controllers;

import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleDetailsRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateVehicleDetailsRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.VehicleDetailsResponseDto;
import com.coders.rentkun.services.VehicleDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle/vehicle-details")
public class VehicleDetailsController {
    private final VehicleDetailsService vehicleDetailsService;

    public VehicleDetailsController(VehicleDetailsService vehicleDetailsService) {
        this.vehicleDetailsService = vehicleDetailsService;
    }

    @PostMapping
    public ResponseEntity<VehicleDetailsResponseDto> saveVehicleDetails(@RequestBody CreateVehicleDetailsRequestDto vehicleDetailsRequestDto) {
        return ResponseEntity.ok(vehicleDetailsService.saveVehicleDetails(vehicleDetailsRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<VehicleDetailsResponseDto>> getAllVehicleDetails() {
        return ResponseEntity.ok(vehicleDetailsService.getAllVehicleDetails());
    }

    @GetMapping("/id/{vehicleDetailsId}")
    public ResponseEntity<VehicleDetailsResponseDto> getVehicleDetailsByVehicleDetailsId(@PathVariable Long vehicleDetailsId) {
        return ResponseEntity.ok(vehicleDetailsService.getVehicleDetailsByVehicleDetailsId(vehicleDetailsId));
    }

    @PutMapping("/{vehicleDetailsId}")
    public ResponseEntity<VehicleDetailsResponseDto> updateVehicleDetails(@PathVariable Long vehicleDetailsId, @RequestBody UpdateVehicleDetailsRequestDto vehicleDetailsRequestDto) {
        return ResponseEntity.ok(vehicleDetailsService.updateVehicleDetails(vehicleDetailsId, vehicleDetailsRequestDto));
    }

    @DeleteMapping("/{vehicleDetailsId}")
    public ResponseEntity<Void> deleteVehicleDetails(@PathVariable Long vehicleDetailsId) {
        vehicleDetailsService.deleteVehicleDetails(vehicleDetailsId);
        return ResponseEntity.ok().build();
    }
}
