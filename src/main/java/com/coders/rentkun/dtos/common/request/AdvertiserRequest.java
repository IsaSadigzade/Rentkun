package com.coders.rentkun.dtos.common.request;

import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertiserRequest {
    private CreateVehicleRequestDto createVehicleRequest;
}
