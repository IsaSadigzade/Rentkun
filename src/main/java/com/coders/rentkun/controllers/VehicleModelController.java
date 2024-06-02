package com.coders.rentkun.controllers;

import com.coders.rentkun.dtos.vehicles.requests.CreateModelRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateModelRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.BrandModelResponseDto;
import com.coders.rentkun.dtos.vehicles.responses.ModelResponseDto;
import com.coders.rentkun.services.vehicles.VehicleModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle/brand/model")
public class VehicleModelController {
    private final VehicleModelService vehicleModelService;

    public VehicleModelController(VehicleModelService vehicleModelService) {
        this.vehicleModelService = vehicleModelService;
    }

    @PostMapping
    public ResponseEntity<ModelResponseDto> saveModel(@RequestBody CreateModelRequestDto modelRequestDto) {
        return ResponseEntity.ok(vehicleModelService.saveModel(modelRequestDto));
    }

    @GetMapping("/active-brands")
    public ResponseEntity<List<BrandModelResponseDto>> getAllModelsWithActiveBrands() {
        return ResponseEntity.ok(vehicleModelService.getAllModelsWithActiveBrands());
    }

    @GetMapping
    public ResponseEntity<List<BrandModelResponseDto>> getAllModelsWithBrands() {
        return ResponseEntity.ok(vehicleModelService.getAllModelsWithBrands());
    }

    @GetMapping("/modelId/{modelId}")
    public ResponseEntity<ModelResponseDto> getModelByModelId(@PathVariable Long modelId) {
        return ResponseEntity.ok(vehicleModelService.getModelByModelId(modelId));
    }

    @GetMapping("/modelName/{modelName}")
    public ResponseEntity<ModelResponseDto> getModelByModelName(@PathVariable String modelName) {
        return ResponseEntity.ok(vehicleModelService.getModelByModelName(modelName));
    }

    @GetMapping("/brandId/{brandId}")
    public ResponseEntity<List<BrandModelResponseDto>> getModelsByBrandId(@PathVariable Long brandId) {
        return ResponseEntity.ok(vehicleModelService.getModelsByBrandId(brandId));
    }

    @GetMapping("/brandName/{brandName}")
    public ResponseEntity<List<BrandModelResponseDto>> getModelsByBrandName(@PathVariable String brandName) {
          return ResponseEntity.ok(vehicleModelService.getModelsByBrandName(brandName));
    }

    @PutMapping("/{modelId}")
    public ResponseEntity<ModelResponseDto> updateModel(@PathVariable Long modelId, @RequestBody UpdateModelRequestDto modelRequestDto) {
        return ResponseEntity.ok(vehicleModelService.updateModel(modelId, modelRequestDto));
    }

    @DeleteMapping("/{modelId}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long modelId) {
        vehicleModelService.deleteBrand(modelId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{modelId}/deactivate")
    public ResponseEntity<Void> deactivateModelByModelId(@PathVariable Long modelId) {
        vehicleModelService.deactivateModelByModelId(modelId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{modelName}/deactivate")
    public ResponseEntity<Void> deactivateModelByModelName(@PathVariable String modelName) {
        vehicleModelService.deactivateModelByModelName(modelName);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{modelId}/activate")
    public ResponseEntity<Void> activateModelByModelId(@PathVariable Long modelId) {
        vehicleModelService.activateModelByModelId(modelId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{modelName}/activate")
    public ResponseEntity<Void> activateModelByModelName(@PathVariable String modelName) {
        vehicleModelService.activateModelByModelName(modelName);
        return ResponseEntity.ok().build();
    }
}
