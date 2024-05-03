package com.coders.rentkun.dtos.vehicles.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleTypeResponseDto {
    private Long id;
    private String vehicleTypeName;
}
