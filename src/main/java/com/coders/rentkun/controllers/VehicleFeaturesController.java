package com.coders.rentkun.controllers;

import com.coders.rentkun.dtos.vehicles.requests.CreateFeatureRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateFeatureRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.FeatureResponseDto;
import com.coders.rentkun.services.vehicles.VehicleFeaturesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle/vehicle-features")
public class VehicleFeaturesController {
    private final VehicleFeaturesService vehicleFeaturesService;

    public VehicleFeaturesController(VehicleFeaturesService vehicleFeaturesService) {
        this.vehicleFeaturesService = vehicleFeaturesService;
    }

    @PostMapping
    public ResponseEntity<FeatureResponseDto> saveFeature(@RequestBody CreateFeatureRequestDto featureRequestDto) {
        return ResponseEntity.ok(vehicleFeaturesService.saveFeature(featureRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<FeatureResponseDto>> getAllFeatures() {
        return ResponseEntity.ok(vehicleFeaturesService.getAllFeatures());
    }

    @GetMapping("/id/{featureId}")
    public ResponseEntity<FeatureResponseDto> getFeatureByFeatureId(@PathVariable Long featureId) {
        return ResponseEntity.ok(vehicleFeaturesService.getFeatureByFeatureId(featureId));
    }

    @GetMapping("/name/{featureName}")
    public ResponseEntity<FeatureResponseDto> getFeatureByFeatureName(@PathVariable String featureName) {
        return ResponseEntity.ok(vehicleFeaturesService.getFeatureByFeatureName(featureName));
    }

    @PutMapping("/{featureId}")
    public ResponseEntity<FeatureResponseDto> updateFeature(@PathVariable Long featureId, @RequestBody UpdateFeatureRequestDto featureRequestDto) {
        return ResponseEntity.ok(vehicleFeaturesService.updateFeature(featureId, featureRequestDto));
    }

    @DeleteMapping("/{featureId}")
    public ResponseEntity<Void> deleteFeature(@PathVariable Long featureId) {
        vehicleFeaturesService.deleteFeature(featureId);
        return ResponseEntity.ok().build();
    }
}
