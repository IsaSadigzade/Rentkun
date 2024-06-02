package com.coders.rentkun.entities.common;

import com.coders.rentkun.entities.common.enums.RentalStatus;
import com.coders.rentkun.entities.users.User;
import com.coders.rentkun.entities.vehicles.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Renter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long rentedDays;
    private BigDecimal pricePerDay;
    private BigDecimal totalRentalPrice;

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

//    @Enumerated(EnumType.STRING)
//    private PaymentStatus paymentStatus;

    public void addRentedDays(long days) {
        this.startDate = LocalDateTime.now();
        this.endDate = startDate.plusDays(days);
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
