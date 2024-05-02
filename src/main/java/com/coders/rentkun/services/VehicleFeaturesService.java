package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.requests.CreateFeatureRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateFeatureRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.FeatureResponseDto;

import java.util.List;

public interface VehicleFeaturesService {
    FeatureResponseDto saveFeature(CreateFeatureRequestDto featureRequestDto);

    List<FeatureResponseDto> getAllFeatures();

    FeatureResponseDto getFeatureByFeatureId(Long featureId);

    FeatureResponseDto getFeatureByFeatureName(String featureName);

    FeatureResponseDto updateFeature(Long featureId, UpdateFeatureRequestDto featureRequestDto);

    void deleteFeature(Long featureId);
}
