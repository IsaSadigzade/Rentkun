package com.coders.rentkun.dtos.common.response;

import com.coders.rentkun.entities.common.enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RenterResponse {
    private Long id;
    private String clientName;
    private String carType;
    private String plateNumber;
    private RentalStatus rentalStatus;

//    private long rentedDays;
//    private long leftDays;
//    private BigDecimal totalRentalPrice;
//    private PaymentStatus paymentStatus;
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
}
