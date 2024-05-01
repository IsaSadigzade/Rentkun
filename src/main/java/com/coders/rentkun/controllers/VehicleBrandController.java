package com.coders.rentkun.controllers;

import com.coders.rentkun.dtos.vehicles.requests.CreateBrandRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateBrandRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.BrandResponseDto;
import com.coders.rentkun.services.VehicleBrandService;
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

    @PatchMapping("/deactivate/{brandName}")
    public ResponseEntity<Void> deactivateBrand(@PathVariable String brandName) {
        vehicleBrandService.deactivateBrand(brandName);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/activate/{brandName}")
    public ResponseEntity<Void> activateBrand(@PathVariable String brandName) {
        vehicleBrandService.activateBrand(brandName);
        return ResponseEntity.ok().build();
    }
}
