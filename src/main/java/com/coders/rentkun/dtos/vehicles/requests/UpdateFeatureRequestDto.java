package com.coders.rentkun.dtos.vehicles.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFeatureRequestDto {
    private String featureName;
}
