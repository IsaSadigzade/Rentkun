package com.coders.rentkun.entities.vehicles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
    private String year;
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "vehicleDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Vehicle vehicle;

    public VehicleDetails(String city, String country, String plateNumber, String numberOfSeats, String distance, String color, String year, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.city = city;
        this.country = country;
        this.plateNumber = plateNumber;
        this.numberOfSeats = numberOfSeats;
        this.distance = distance;
        this.color = color;
        this.year = year;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
