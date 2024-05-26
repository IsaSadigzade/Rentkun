package com.coders.rentkun.dtos.common.request;

import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddNewAdvertRequest {
    private CreateVehicleRequestDto createVehicleRequest;
    private BigDecimal price;
    private LocalDate availableFromDate;
    private long additionalDays;
}
