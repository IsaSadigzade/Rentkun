package com.coders.rentkun.dtos.common.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdvertTimeRequest {
    private LocalDate availableFromDate;
    private long additionalDays;
}
