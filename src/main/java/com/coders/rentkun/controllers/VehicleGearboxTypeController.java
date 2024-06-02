package com.coders.rentkun.controllers;

import com.coders.rentkun.dtos.vehicles.requests.CreateGearboxTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateGearboxTypeRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.GearboxTypeResponseDto;
import com.coders.rentkun.services.vehicles.VehicleGearboxTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle/vehicle-gearbox-type")
public class VehicleGearboxTypeController {
    private final VehicleGearboxTypeService vehicleGearboxTypeService;

    public VehicleGearboxTypeController(VehicleGearboxTypeService vehicleGearboxTypeService) {
        this.vehicleGearboxTypeService = vehicleGearboxTypeService;
    }

    @PostMapping
    public ResponseEntity<GearboxTypeResponseDto> saveGearboxType(@RequestBody CreateGearboxTypeRequestDto GearboxTypeRequestDto) {
        return ResponseEntity.ok(vehicleGearboxTypeService.saveGearboxType(GearboxTypeRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<GearboxTypeResponseDto>> getAllGearboxTypes() {
        return ResponseEntity.ok(vehicleGearboxTypeService.getAllGearboxTypes());
    }

    @GetMapping("/id/{GearboxTypeId}")
    public ResponseEntity<GearboxTypeResponseDto> getGearboxTypeByGearboxTypeId(@PathVariable Long GearboxTypeId) {
        return ResponseEntity.ok(vehicleGearboxTypeService.getGearboxTypeByGearboxTypeId(GearboxTypeId));
    }

    @GetMapping("/name/{GearboxTypeName}")
    public ResponseEntity<GearboxTypeResponseDto> getGearboxTypeByGearboxTypeName(@PathVariable String GearboxTypeName) {
        return ResponseEntity.ok(vehicleGearboxTypeService.getGearboxTypeByGearboxTypeName(GearboxTypeName));
    }

    @PutMapping("/{GearboxTypeId}")
    public ResponseEntity<GearboxTypeResponseDto> updateGearboxType(@PathVariable Long GearboxTypeId, @RequestBody UpdateGearboxTypeRequestDto GearboxTypeRequestDto) {
        return ResponseEntity.ok(vehicleGearboxTypeService.updateGearboxType(GearboxTypeId, GearboxTypeRequestDto));
    }

    @DeleteMapping("/{GearboxTypeId}")
    public ResponseEntity<Void> deleteGearboxType(@PathVariable Long GearboxTypeId) {
        vehicleGearboxTypeService.deleteGearboxType(GearboxTypeId);
        return ResponseEntity.ok().build();
    }
}
