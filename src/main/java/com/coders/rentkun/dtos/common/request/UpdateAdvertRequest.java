package com.coders.rentkun.dtos.common.request;

import com.coders.rentkun.dtos.vehicles.requests.UpdateVehicleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdvertRequest {
    private UpdateVehicleRequestDto updateVehicleRequestDto;
    private BigDecimal price;
}
