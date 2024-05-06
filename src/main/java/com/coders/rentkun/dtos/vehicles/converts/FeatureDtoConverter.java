package com.coders.rentkun.dtos.vehicles.converts;

import com.coders.rentkun.dtos.vehicles.requests.CreateFeatureRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateFeatureRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.FeatureResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleFeature;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FeatureDtoConverter {
    public FeatureResponseDto convertToResponse(VehicleFeature entity) {
        return new FeatureResponseDto(entity.getId(), entity.getName());
    }

    public Set<FeatureResponseDto> convertToSetResponse(Set<VehicleFeature> entities) {
        return entities.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toSet());
    }

    public VehicleFeature convertToEntity(CreateFeatureRequestDto featureRequestDto) {
        VehicleFeature feature = new VehicleFeature();
        feature.setName(featureRequestDto.getFeatureName());
        return feature;
    }

    public VehicleFeature convertToEntity(VehicleFeature foundFeature, UpdateFeatureRequestDto featureRequestDto) {
        foundFeature.setName(featureRequestDto.getFeatureName());
        return foundFeature;
    }
}
