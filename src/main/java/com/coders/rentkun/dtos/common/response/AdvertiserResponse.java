package com.coders.rentkun.dtos.common.response;

import com.coders.rentkun.dtos.vehicles.responses.VehicleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertiserResponse {
    private Long id;
    private String username;
    private VehicleResponseDto vehicleResponse;
    private BigDecimal price;
    private LocalDate availableFromDate;
    private LocalDate availableToDate;
}
