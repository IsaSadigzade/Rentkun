package com.coders.rentkun.services.impl;

import com.coders.rentkun.dtos.vehicles.converts.FeatureDtoConverter;
import com.coders.rentkun.dtos.vehicles.requests.CreateFeatureRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateFeatureRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.FeatureResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleFeature;
import com.coders.rentkun.exception.FeatureDoesNotExistException;
import com.coders.rentkun.exception.FeatureNotFoundException;
import com.coders.rentkun.repositories.VehicleFeaturesRepository;
import com.coders.rentkun.services.VehicleFeaturesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleFeaturesServiceImpl implements VehicleFeaturesService {
    private final VehicleFeaturesRepository vehicleFeaturesRepository;
    private final FeatureDtoConverter featureDtoConverter;

    public VehicleFeaturesServiceImpl(VehicleFeaturesRepository vehicleFeaturesRepository, FeatureDtoConverter featureDtoConverter) {
        this.vehicleFeaturesRepository = vehicleFeaturesRepository;
        this.featureDtoConverter = featureDtoConverter;
    }

    @Override
    public FeatureResponseDto saveFeature(CreateFeatureRequestDto featureRequestDto) {
        return featureDtoConverter.convertToResponse(
                vehicleFeaturesRepository.save(
                        featureDtoConverter.convertToEntity(featureRequestDto)
                )
        );
    }

    @Override
    public List<FeatureResponseDto> getAllFeatures() {
        return vehicleFeaturesRepository.findAll().stream()
                .map(featureDtoConverter::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FeatureResponseDto getFeatureByFeatureId(Long featureId) {
        return featureDtoConverter.convertToResponse(findByFeatureId(featureId));
    }

    @Override
    public FeatureResponseDto getFeatureByFeatureName(String featureName) {
        return featureDtoConverter.convertToResponse(findByFeatureName(featureName));
    }

    @Override
    public FeatureResponseDto updateFeature(Long featureId, UpdateFeatureRequestDto featureRequestDto) {
        VehicleFeature vehicleFeature = findByFeatureId(featureId);
        return featureDtoConverter.convertToResponse(
                vehicleFeaturesRepository.save(
                        featureDtoConverter.convertToEntity(vehicleFeature, featureRequestDto)
                )
        );
    }

    @Override
    public void deleteFeature(Long featureId) {
        if(isFeatureExist(featureId)) {
            vehicleFeaturesRepository.deleteById(featureId);
        } else {
            throw new FeatureDoesNotExistException("Feature doesn't exist by following featureId: " + featureId);
        }
    }

    public VehicleFeature findByFeatureId(Long featureId) {
        return vehicleFeaturesRepository.findById(featureId)
                .orElseThrow(() -> new FeatureNotFoundException("Vehicle Feature couldn't be found by following id: " + featureId));
    }

    public VehicleFeature findByFeatureName(String featureName) {
        return vehicleFeaturesRepository.findByName(featureName)
                .orElseThrow(() -> new FeatureNotFoundException("Vehicle Feature couldn't be found by following featureName: " + featureName));
    }

    private boolean isFeatureExist(Long featureId) {
        return vehicleFeaturesRepository.existsById(featureId);
    }
}
