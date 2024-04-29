package com.coders.rentkun.entities.common;

import com.coders.rentkun.entities.users.User;
import com.coders.rentkun.entities.vehicles.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Renter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private short rentalDuration;
    private boolean vehicleActive;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private Vehicle vehicle;
}
