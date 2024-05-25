package com.coders.rentkun.dtos.common.response;

import com.coders.rentkun.dtos.vehicles.responses.VehicleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertiserResponse {
    private Long id;
    private VehicleResponseDto vehicleResponse;
}
