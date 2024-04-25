package com.coders.rentkun.entities.vehicles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VehicleDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String country;
    private String plateNumber;
    private String numberOfSeats;
    private String distance;
    private String color;
    private LocalDate year;
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate updatedAt;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Vehicle vehicle;
}
