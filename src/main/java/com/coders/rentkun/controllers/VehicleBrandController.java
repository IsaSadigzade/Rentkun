package com.coders.rentkun.controllers;

import com.coders.rentkun.dtos.vehicles.requests.CreateBrandRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateBrandRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.BrandResponseDto;
import com.coders.rentkun.services.vehicles.VehicleBrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle/brand")
public class VehicleBrandController {
    private final VehicleBrandService vehicleBrandService;

    public VehicleBrandController(VehicleBrandService vehicleBrandService) {
        this.vehicleBrandService = vehicleBrandService;
    }

    @PostMapping
    public ResponseEntity<BrandResponseDto> saveBrand(@RequestBody CreateBrandRequestDto brandRequestDto) {
        return ResponseEntity.ok(vehicleBrandService.saveBrand(brandRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<BrandResponseDto>> getAllBrands() {
        return ResponseEntity.ok(vehicleBrandService.getAllBrands());
    }

    @GetMapping("/id/{brandId}")
    public ResponseEntity<BrandResponseDto> getBrandByBrandId(@PathVariable Long brandId) {
        return ResponseEntity.ok(vehicleBrandService.getBrandByBrandId(brandId));
    }

    @GetMapping("/name/{brandName}")
    public ResponseEntity<BrandResponseDto> getBrandByBrandName(@PathVariable String brandName) {
        return ResponseEntity.ok(vehicleBrandService.getBrandByBrandName(brandName));
    }

    @PutMapping("/{brandId}")
    public ResponseEntity<BrandResponseDto> updateBrand(@PathVariable Long brandId, @RequestBody UpdateBrandRequestDto brandRequestDto) {
        return ResponseEntity.ok(vehicleBrandService.updateBrand(brandId, brandRequestDto));
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long brandId) {
        vehicleBrandService.deleteBrand(brandId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{brandId}/deactivate")
    public ResponseEntity<Void> deactivateBrandByBrandId(@PathVariable Long brandId) {
        vehicleBrandService.deactivateBrandByBrandId(brandId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{brandName}/deactivate")
    public ResponseEntity<Void> deactivateBrandByBrandName(@PathVariable String brandName) {
        vehicleBrandService.deactivateBrandByBrandName(brandName);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{brandId}/activate")
    public ResponseEntity<Void> activateBrandByBrandId(@PathVariable Long brandId) {
        vehicleBrandService.activateBrandByBrandId(brandId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{brandName}/activate")
    public ResponseEntity<Void> activateBrandByBrandName(@PathVariable String brandName) {
        vehicleBrandService.activateBrandByBrandName(brandName);
        return ResponseEntity.ok().build();
    }
}
