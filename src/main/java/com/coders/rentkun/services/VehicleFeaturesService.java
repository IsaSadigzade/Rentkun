package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.converts.FeatureDtoConverter;
import com.coders.rentkun.dtos.vehicles.requests.CreateFeatureRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateFeatureRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.FeatureResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleFeature;
import com.coders.rentkun.exception.FeatureDoesNotExistException;
import com.coders.rentkun.exception.FeatureNotFoundException;
import com.coders.rentkun.repositories.VehicleFeaturesRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VehicleFeaturesService {
    private final VehicleFeaturesRepository vehicleFeaturesRepository;
    private final FeatureDtoConverter featureDtoConverter;

    public VehicleFeaturesService(VehicleFeaturesRepository vehicleFeaturesRepository, FeatureDtoConverter featureDtoConverter) {
        this.vehicleFeaturesRepository = vehicleFeaturesRepository;
        this.featureDtoConverter = featureDtoConverter;
    }

    public FeatureResponseDto saveFeature(CreateFeatureRequestDto featureRequestDto) {
        return featureDtoConverter.convertToResponse(
                vehicleFeaturesRepository.save(
                        featureDtoConverter.convertToEntity(featureRequestDto)
                )
        );
    }

    public List<FeatureResponseDto> getAllFeatures() {
        return vehicleFeaturesRepository.findAll().stream()
                .map(featureDtoConverter::convertToResponse)
                .collect(Collectors.toList());
    }

    public FeatureResponseDto getFeatureByFeatureId(Long featureId) {
        return featureDtoConverter.convertToResponse(findByFeatureId(featureId));
    }

    public FeatureResponseDto getFeatureByFeatureName(String featureName) {
        return featureDtoConverter.convertToResponse(findByFeatureName(featureName));
    }

    public FeatureResponseDto updateFeature(Long featureId, UpdateFeatureRequestDto featureRequestDto) {
        VehicleFeature vehicleFeature = findByFeatureId(featureId);
        return featureDtoConverter.convertToResponse(
                vehicleFeaturesRepository.save(
                        featureDtoConverter.convertToEntity(vehicleFeature, featureRequestDto)
                )
        );
    }

    public void deleteFeature(Long featureId) {
        if(isFeatureExist(featureId)) {
            vehicleFeaturesRepository.deleteById(featureId);
        } else {
            throw new FeatureDoesNotExistException("Feature doesn't exist by following featureId: " + featureId);
        }
    }

    protected Set<VehicleFeature> findFeaturesByIds(Set<Long> featureIds) {
        Set<VehicleFeature> features = new HashSet<>();
        for (Long featureId : featureIds) {
            VehicleFeature featureOptional = findByFeatureId(featureId);
            features.add(featureOptional);
        }
        return features;
    }

    protected VehicleFeature findByFeatureId(Long featureId) {
        return vehicleFeaturesRepository.findById(featureId)
                .orElseThrow(() -> new FeatureNotFoundException("Vehicle Feature couldn't be found by following id: " + featureId));
    }

    protected VehicleFeature findByFeatureName(String featureName) {
        return vehicleFeaturesRepository.findByName(featureName)
                .orElseThrow(() -> new FeatureNotFoundException("Vehicle Feature couldn't be found by following featureName: " + featureName));
    }

    protected boolean isFeatureExist(Long featureId) {
        return vehicleFeaturesRepository.existsById(featureId);
    }
}
