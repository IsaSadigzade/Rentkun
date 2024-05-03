package com.coders.rentkun.controllers;

import com.coders.rentkun.dtos.vehicles.requests.CreateFuelTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateFuelTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.FuelTypeResponseDto;
import com.coders.rentkun.services.VehicleFuelTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle/vehicle-fuel-type")
public class VehicleFuelTypeController {
    private final VehicleFuelTypeService vehicleFuelTypeService;

    public VehicleFuelTypeController(VehicleFuelTypeService vehicleFuelTypeService) {
        this.vehicleFuelTypeService = vehicleFuelTypeService;
    }

    @PostMapping
    public ResponseEntity<FuelTypeResponseDto> saveFuelType(@RequestBody CreateFuelTypeRequestDto FuelTypeRequestDto) {
        return ResponseEntity.ok(vehicleFuelTypeService.saveFuelType(FuelTypeRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<FuelTypeResponseDto>> getAllFuelTypes() {
        return ResponseEntity.ok(vehicleFuelTypeService.getAllFuelTypes());
    }

    @GetMapping("/id/{fuelTypeId}")
    public ResponseEntity<FuelTypeResponseDto> getFuelTypeByFuelTypeId(@PathVariable Long fuelTypeId) {
        return ResponseEntity.ok(vehicleFuelTypeService.getFuelTypeByFuelTypeId(fuelTypeId));
    }

    @GetMapping("/name/{fuelTypeName}")
    public ResponseEntity<FuelTypeResponseDto> getFuelTypeByFuelTypeName(@PathVariable String fuelTypeName) {
        return ResponseEntity.ok(vehicleFuelTypeService.getFuelTypeByFuelTypeName(fuelTypeName));
    }

    @PutMapping("/{fuelTypeId}")
    public ResponseEntity<FuelTypeResponseDto> updateFuelType(@PathVariable Long fuelTypeId, @RequestBody UpdateFuelTypeRequestDto FuelTypeRequestDto) {
        return ResponseEntity.ok(vehicleFuelTypeService.updateFuelType(fuelTypeId, FuelTypeRequestDto));
    }

    @DeleteMapping("/{fuelTypeId}")
    public ResponseEntity<Void> deleteFuelType(@PathVariable Long fuelTypeId) {
        vehicleFuelTypeService.deleteFuelType(fuelTypeId);
        return ResponseEntity.ok().build();
    }
}
