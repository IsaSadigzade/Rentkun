package com.coders.rentkun.controllers;

import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateVehicleTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.VehicleTypeResponseDto;
import com.coders.rentkun.services.VehicleTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle/vehicle-type")
public class VehicleTypeController {
    private final VehicleTypeService vehicleTypeService;

    public VehicleTypeController(VehicleTypeService vehicleTypeService) {
        this.vehicleTypeService = vehicleTypeService;
    }

    @PostMapping
    public ResponseEntity<VehicleTypeResponseDto> saveVehicleType(@RequestBody CreateVehicleTypeRequestDto vehicleTypeRequestDto) {
        return ResponseEntity.ok(vehicleTypeService.saveVehicleType(vehicleTypeRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<VehicleTypeResponseDto>> getAllVehicleTypes() {
        return ResponseEntity.ok(vehicleTypeService.getAllVehicleTypes());
    }

    @GetMapping("/id/{vehicleTypeId}")
    public ResponseEntity<VehicleTypeResponseDto> getVehicleTypeByVehicleTypeId(@PathVariable Long vehicleTypeId) {
        return ResponseEntity.ok(vehicleTypeService.getVehicleTypeByVehicleTypeId(vehicleTypeId));
    }

    @GetMapping("/name/{vehicleTypeName}")
    public ResponseEntity<VehicleTypeResponseDto> getVehicleTypeByVehicleTypeName(@PathVariable String vehicleTypeName) {
        return ResponseEntity.ok(vehicleTypeService.getVehicleTypeByVehicleTypeName(vehicleTypeName));
    }

    @PutMapping("/{vehicleTypeId}")
    public ResponseEntity<VehicleTypeResponseDto> updateVehicleType(@PathVariable Long vehicleTypeId, @RequestBody UpdateVehicleTypeRequestDto vehicleTypeRequestDto) {
        return ResponseEntity.ok(vehicleTypeService.updateVehicleType(vehicleTypeId, vehicleTypeRequestDto));
    }

    @DeleteMapping("/{vehicleTypeId}")
    public ResponseEntity<Void> deleteVehicleType(@PathVariable Long vehicleTypeId) {
        vehicleTypeService.deleteVehicleType(vehicleTypeId);
        return ResponseEntity.ok().build();
    }
}
